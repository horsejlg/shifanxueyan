/**
 * 修正了之前SdxzConstants为空的错误
 * 添加了使用外部Freemarker模版功能和字符串模板功能
 * controller方法中返回filebasepath.ftl时解析器会从资源路径中查找SdxzConstants的setTemplateFilePath方法设定的模版
 * controller方法中返回string.ftl时解析器会通过SdxzConstants的setTemplate方法设定的模版
 * 在用户没有登录时不至于user对象为空
 */

package cn.qlt.utils.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextException;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import cn.qlt.domain.User;
import cn.qlt.utils.EnvironmentUtils;
import freemarker.core.ParseException;
import freemarker.ext.jsp.TaglibFactory;
import freemarker.ext.servlet.AllHttpScopesHashModel;
import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.ext.servlet.HttpRequestHashModel;
import freemarker.ext.servlet.HttpRequestParametersHashModel;
import freemarker.ext.servlet.HttpSessionHashModel;
import freemarker.ext.servlet.ServletContextHashModel;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * View using the FreeMarker template engine.
 *
 * <p>
 * Exposes the following JavaBean properties:
 * <ul>
 * <li><b>url</b>: the location of the FreeMarker template to be wrapped,
 * relative to the FreeMarker template context (directory).
 * <li><b>encoding</b> (optional, default is determined by FreeMarker
 * configuration): the encoding of the FreeMarker template file
 * </ul>
 *
 * <p>
 * Depends on a single {@link FreeMarkerConfig} object such as
 * {@link FreeMarkerConfigurer} being accessible in the current web application
 * context, with any bean name. Alternatively, you can set the FreeMarker
 * {@link Configuration} object as bean property. See {@link #setConfiguration}
 * for more details on the impacts of this approach.
 *
 * <p>
 * Note: Spring's FreeMarker support requires FreeMarker 2.3 or higher.
 *
 * @author Darren Davison
 * @author Juergen Hoeller
 * @since 03.03.2004
 * @see #setUrl
 * @see #setExposeSpringMacroHelpers
 * @see #setEncoding
 * @see #setConfiguration
 * @see FreeMarkerConfig
 * @see FreeMarkerConfigurer
 */
public class CustomFreeMarkerView extends QltFreeMarkerView {

	public static final String STRING = "string.ftl";

	public static final String FILEBASEPATH = "file.ftl";

	private String encoding;

	private Configuration configuration;

	private TaglibFactory taglibFactory;

	public void setTaglibFactory(TaglibFactory taglibFactory) {
		this.taglibFactory = taglibFactory;
	}

	private ServletContextHashModel servletContextHashModel;

	/**
	 * Set the encoding of the FreeMarker template file. Default is determined
	 * by the FreeMarker Configuration: "ISO-8859-1" if not specified otherwise.
	 * <p>
	 * Specify the encoding in the FreeMarker Configuration rather than per
	 * template if all your templates share a common encoding.
	 */
	@Override
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * Return the encoding for the FreeMarker template.
	 */
	@Override
	protected String getEncoding() {
		return this.encoding;
	}

	/**
	 * Set the FreeMarker Configuration to be used by this view.
	 * <p>
	 * If this is not set, the default lookup will occur: a single
	 * {@link FreeMarkerConfig} is expected in the current web application
	 * context, with any bean name. <strong>Note:</strong> using this method
	 * will cause a new instance of {@link TaglibFactory} to created for every
	 * single {@link FreeMarkerView} instance. This can be quite expensive in
	 * terms of memory and initial CPU usage. In production it is recommended
	 * that you use a {@link FreeMarkerConfig} which exposes a single shared
	 * {@link TaglibFactory}.
	 */
	@Override
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * Return the FreeMarker configuration used by this view.
	 */
	@Override
	protected Configuration getConfiguration() {
		return this.configuration;
	}

	/**
	 * Invoked on startup. Looks for a single FreeMarkerConfig bean to find the
	 * relevant Configuration for this factory.
	 * <p>
	 * Checks that the template for the default Locale can be found: FreeMarker
	 * will check non-Locale-specific templates if a locale-specific one is not
	 * found.
	 * 
	 * @see freemarker.cache.TemplateCache#getTemplate
	 */
	@Override
	protected void initServletContext(ServletContext servletContext)
			throws BeansException {
		if (getConfiguration() != null) {
			this.taglibFactory = new TaglibFactory(servletContext);
		} else {
			FreeMarkerConfig config = autodetectConfiguration();
			setConfiguration(config.getConfiguration());
			this.taglibFactory = config.getTaglibFactory();
		}

		GenericServlet servlet = new CustomGenericServletAdapter();
		try {
			servlet.init(new CustomDelegatingServletConfig());
		} catch (ServletException ex) {
			throw new BeanInitializationException(
					"Initialization of GenericServlet adapter failed", ex);
		}
		this.servletContextHashModel = new ServletContextHashModel(servlet,
				getObjectWrapper());
		FreemarkerViewCondition.init(getApplicationContext(), configuration,
				getObjectWrapper(), servletContext, servlet);
	}

	/**
	 * Autodetect a {@link FreeMarkerConfig} object via the ApplicationContext.
	 * 
	 * @return the Configuration instance to use for FreeMarkerViews
	 * @throws BeansException
	 *             if no Configuration instance could be found
	 * @see #getApplicationContext
	 * @see #setConfiguration
	 */
	@Override
	protected FreeMarkerConfig autodetectConfiguration() throws BeansException {
		try {
			ApplicationContext applicationContext = getApplicationContext();
			return BeanFactoryUtils.beanOfTypeIncludingAncestors(
					applicationContext, FreeMarkerConfig.class, true, false);
		} catch (NoSuchBeanDefinitionException ex) {
			throw new ApplicationContextException(
					"Must define a single FreeMarkerConfig bean in this web application context "
							+ "(may be inherited): FreeMarkerConfigurer is the usual implementation. "
							+ "This bean may be given any name.", ex);
		}
	}

	/**
	 * Return the configured FreeMarker {@link ObjectWrapper}, or the
	 * {@link ObjectWrapper#DEFAULT_WRAPPER default wrapper} if none specified.
	 * 
	 * @see freemarker.template.Configuration#getObjectWrapper()
	 */
	@Override
	protected ObjectWrapper getObjectWrapper() {
		ObjectWrapper ow = getConfiguration().getObjectWrapper();
		return (ow != null ? ow : ObjectWrapper.DEFAULT_WRAPPER);
	}

	/**
	 * Check that the FreeMarker template used for this view exists and is
	 * valid.
	 * <p>
	 * Can be overridden to customize the behavior, for example in case of
	 * multiple templates to be rendered into a single view.
	 */
	@Override
	public boolean checkResource(Locale locale) throws Exception {
		try {
			// Check that we can get the template, even if we might subsequently
			// get it again.
			getTemplate(getUrl(), locale);
			return true;
		} catch (FileNotFoundException ex) {
			if (logger.isDebugEnabled()) {
				logger.debug("No FreeMarker view found for URL: " + getUrl());
			}
			return false;
		} catch (ParseException ex) {
			throw new ApplicationContextException(
					"Failed to parse FreeMarker template for URL [" + getUrl()
							+ "]", ex);
		} catch (IOException ex) {
			throw new ApplicationContextException(
					"Could not load FreeMarker template for URL [" + getUrl()
							+ "]", ex);
		}
	}

	/**
	 * Process the model map by merging it with the FreeMarker template. Output
	 * is directed to the servlet response.
	 * <p>
	 * This method can be overridden if custom behavior is needed.
	 */
	@Override
	protected void renderMergedTemplateModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		exposeHelpers(model, request);
		doRender(model, request, response);
	}

	/**
	 * Expose helpers unique to each rendering operation. This is necessary so
	 * that different rendering operations can't overwrite each other's formats
	 * etc.
	 * <p>
	 * Called by <code>renderMergedTemplateModel</code>. The default
	 * implementation is empty. This method can be overridden to add custom
	 * helpers to the model.
	 * 
	 * @param model
	 *            The model that will be passed to the template at merge time
	 * @param request
	 *            current HTTP request
	 * @throws Exception
	 *             if there's a fatal error while we're adding information to
	 *             the context
	 * @see #renderMergedTemplateModel
	 */
	@Override
	protected void exposeHelpers(Map<String, Object> model,
			HttpServletRequest request) throws Exception {
		// ************* PATCHED BY FIRMGOAL *************
		model.put("base", request.getContextPath());
		// edit by bzh 2011.8.9
		// model.put("base",
		// request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/");
		// ************* PATCHED BY FIRMGOAL *************
		// add by bzh 2011.8.9
		model.put("serverName", request.getServerName());
		model.put("serverPort", request.getServerPort());
		model.put("baseUrl",
				request.getScheme() + "://" + request.getServerName() + ":"
						+ request.getServerPort() + request.getContextPath()
						+ "/");
		model.put("requestURI", request.getRequestURI());
		// add by bzh 2011.8.9
	}

	/**
	 * Render the FreeMarker view to the given response, using the given model
	 * map which contains the complete template model to use.
	 * <p>
	 * The default implementation renders the template specified by the "url"
	 * bean property, retrieved via <code>getTemplate</code>. It delegates to
	 * the <code>processTemplate</code> method to merge the template instance
	 * with the given template model.
	 * <p>
	 * Adds the standard Freemarker hash models to the model: request
	 * parameters, request, session and application (ServletContext), as well as
	 * the JSP tag library hash model.
	 * <p>
	 * Can be overridden to customize the behavior, for example to render
	 * multiple templates into a single view.
	 * 
	 * @param model
	 *            the model to use for rendering
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current servlet response
	 * @throws IOException
	 *             if the template file could not be retrieved
	 * @throws Exception
	 *             if rendering failed
	 * @see #setUrl
	 * @see org.springframework.web.servlet.support.RequestContextUtils#getLocale
	 * @see #getTemplate(java.util.Locale)
	 * @see #processTemplate
	 * @see freemarker.ext.servlet.FreemarkerServlet
	 */
	@Override
	protected void doRender(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// Expose model to JSP tags (as request attributes).
		exposeModelAsRequestAttributes(model, request);
		// Expose all standard FreeMarker hash models.
		SimpleHash fmModel = buildTemplateModel(model, request, response);

		if (logger.isDebugEnabled()) {
			logger.debug("Rendering FreeMarker template [" + getUrl()
					+ "] in FreeMarkerView '" + getBeanName() + "'");
		}
		// Grab the locale-specific version of the template.
		Locale locale = RequestContextUtils.getLocale(request);
		processTemplate(getTemplate(locale), fmModel, response);
	}

	/**
	 * Build a FreeMarker template model for the given model Map.
	 * <p>
	 * The default implementation builds a {@link AllHttpScopesHashModel}.
	 * 
	 * @param model
	 *            the model to use for rendering
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current servlet response
	 * @return the FreeMarker template model, as a {@link SimpleHash} or
	 *         subclass thereof
	 */
	@Override
	protected SimpleHash buildTemplateModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response) {
		ObjectWrapper objectWrapper = getObjectWrapper();
		AllHttpScopesHashModel fmModel = new AllHttpScopesHashModel(
				objectWrapper, getServletContext(), request);
		fmModel.put(FreemarkerServlet.KEY_JSP_TAGLIBS, this.taglibFactory);
		fmModel.put(FreemarkerServlet.KEY_APPLICATION,
				this.servletContextHashModel);
		fmModel.put(FreemarkerServlet.KEY_SESSION,
				buildSessionModel(request, response));
		fmModel.put(FreemarkerServlet.KEY_REQUEST, new HttpRequestHashModel(
				request, response, objectWrapper));
		fmModel.put(FreemarkerServlet.KEY_REQUEST_PARAMETERS,
				new HttpRequestParametersHashModel(request));
		User user = AuthUtil.getCurrentUser();
		if (user != null) {
			fmModel.put("user", user);
		}
		fmModel.putAll(model);
		return fmModel;
	}

	/**
	 * Build a FreeMarker {@link HttpSessionHashModel} for the given request,
	 * detecting whether a session already exists and reacting accordingly.
	 * 
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current servlet response
	 * @return the FreeMarker HttpSessionHashModel
	 */
	private HttpSessionHashModel buildSessionModel(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			return new HttpSessionHashModel(session, getObjectWrapper());
		} else {
			return new HttpSessionHashModel(null, request, response,
					getObjectWrapper());
		}
	}

	/**
	 * Retrieve the FreeMarker template for the given locale, to be rendering by
	 * this view.
	 * <p>
	 * By default, the template specified by the "url" bean property will be
	 * retrieved.
	 * 
	 * @param locale
	 *            the current locale
	 * @return the FreeMarker template to render
	 * @throws IOException
	 *             if the template file could not be retrieved
	 * @see #setUrl
	 * @see #getTemplate(String, java.util.Locale)
	 */
	@Override
	protected Template getTemplate(Locale locale) throws IOException {
		String url = getUrl();
		if (FILEBASEPATH.equals(url.toLowerCase())) {
			return new Template(url, new InputStreamReader(new FileInputStream(
					EnvironmentUtils.currentEnvironment().getTemplate()), "utf-8"), getConfiguration());
		} else if (STRING.equals(url.toLowerCase())) {
			return new Template(System.currentTimeMillis() + "",
					new StringReader(EnvironmentUtils.currentEnvironment().getTemplate()),
					getConfiguration());
		} else {
			return getTemplate(url, locale);
		}
	}

	/**
	 * Retrieve the FreeMarker template specified by the given name, using the
	 * encoding specified by the "encoding" bean property.
	 * <p>
	 * Can be called by subclasses to retrieve a specific template, for example
	 * to render multiple templates into a single view.
	 * 
	 * @param name
	 *            the file name of the desired template
	 * @param locale
	 *            the current locale
	 * @return the FreeMarker template
	 * @throws IOException
	 *             if the template file could not be retrieved
	 */
	@Override
	protected Template getTemplate(String name, Locale locale)
			throws IOException {
		return (getEncoding() != null ? getConfiguration().getTemplate(name,
				locale, getEncoding()) : getConfiguration().getTemplate(name,
				locale));
	}

	/**
	 * Process the FreeMarker template to the servlet response.
	 * <p>
	 * Can be overridden to customize the behavior.
	 * 
	 * @param template
	 *            the template to process
	 * @param model
	 *            the model for the template
	 * @param response
	 *            servlet response (use this to get the OutputStream or Writer)
	 * @throws IOException
	 *             if the template file could not be retrieved
	 * @throws TemplateException
	 *             if thrown by FreeMarker
	 * @see freemarker.template.Template#process(Object, java.io.Writer)
	 */
	@Override
	protected void processTemplate(Template template, SimpleHash model,
			HttpServletResponse response) throws IOException, TemplateException {

		template.process(model, response.getWriter());
	}

	public void outToFile(String path, Map<String, Object> modelMap, File file,
			HttpServletRequest request, HttpServletResponse response) {
		modelMap.put("accessPath", "");
		try {
			File outpath = new File(path).getParentFile();
			if (!outpath.exists() || !outpath.isDirectory())
				outpath.mkdirs();
			Template template = new Template(path, new InputStreamReader(
					new FileInputStream(file), "utf-8"), getConfiguration());
			SimpleHash model = new SimpleHash(modelMap);// buildTemplateModel(modelMap,request,response);
			template.process(model, new FileWriter(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Simple adapter class that extends {@link GenericServlet}. Needed for JSP
	 * access in FreeMarker.
	 */
	private static class CustomGenericServletAdapter extends GenericServlet {

		@Override
		public void service(ServletRequest servletRequest,
				ServletResponse servletResponse) {
			// no-op
		}
	}

	/**
	 * Internal implementation of the {@link ServletConfig} interface, to be
	 * passed to the servlet adapter.
	 */
	private class CustomDelegatingServletConfig implements ServletConfig {

		@Override
		public String getServletName() {
			return CustomFreeMarkerView.this.getBeanName();
		}

		@Override
		public ServletContext getServletContext() {
			return CustomFreeMarkerView.this.getServletContext();
		}

		@Override
		public String getInitParameter(String paramName) {
			return null;
		}

		@Override
		public Enumeration<String> getInitParameterNames() {
			return Collections.enumeration(new HashSet<String>());
		}
	}

}
