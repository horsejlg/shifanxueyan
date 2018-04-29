package cn.qlt.utils.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;

import cn.qlt.domain.User;
import cn.qlt.utils.EnvironmentUtils;
import freemarker.ext.jsp.TaglibFactory;
import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.ext.servlet.ServletContextHashModel;
import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class FreemarkerViewCondition {

	private static FreemarkerViewCondition instance;

	private Configuration configuration;

	private SimpleHash simpleHash;

	private FreemarkerViewCondition(ApplicationContext applicationContext, Configuration configuration,
			ObjectWrapper objectWrapper, ServletContext context, GenericServlet servlet) {
		this.configuration = configuration;
		simpleHash = new SimpleHttpScopesHashModel(objectWrapper, context);
		simpleHash.put(FreemarkerServlet.KEY_JSP_TAGLIBS, new TaglibFactory(context));
		simpleHash.put(FreemarkerServlet.KEY_APPLICATION, new ServletContextHashModel(servlet, objectWrapper));
		simpleHash.put("base", EnvironmentUtils.currentEnvironment().getBase());
		simpleHash.put("servicepath", EnvironmentUtils.currentEnvironment().getServicePath());
		simpleHash.put(FreemarkerServlet.KEY_SESSION, EnvironmentUtils.currentEnvironment().getSession());
		HttpServletRequest request = EnvironmentUtils.currentEnvironment().getRequest();
		simpleHash.put(FreemarkerServlet.KEY_REQUEST, request != null ? request : new SimpleHash());
		SimpleHash hash = new SimpleHash();
		if (request != null) {
			Map<String, String[]> map = request.getParameterMap();
			for (Map.Entry<String, String[]> entry : map.entrySet()) {
				hash.put(entry.getKey(), entry.getValue().length > 1 ? entry.getValue() : entry.getValue()[0]);
			}
		}
		simpleHash.put(FreemarkerServlet.KEY_REQUEST_PARAMETERS, hash);
		User user = AuthUtil.getCurrentUser();
			if(user!=null){
				simpleHash.put("user", user);
			}
	}

	public static void init(ApplicationContext applicationContext, Configuration configuration,
			ObjectWrapper objectWrapper, ServletContext context, GenericServlet servlet) {
		instance = new FreemarkerViewCondition(applicationContext, configuration, objectWrapper, context, servlet);
	}

	public static synchronized void write(String path, Map<String, Object> modelMap, File file) {
		Writer out = null;
		try {
			Template template = new Template(file.getName(), new InputStreamReader(new FileInputStream(path), "utf-8"),
					instance.configuration);
			SimpleHash model = new SimpleHash(instance.simpleHash.toMap());
			model.putAll(modelMap);
			out = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
			template.process(model, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class SimpleHttpScopesHashModel extends SimpleHash {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8443051425124918269L;
		private final ServletContext context;
		private final Map unlistedModels = new HashMap();

		public SimpleHttpScopesHashModel(ObjectWrapper wrapper, ServletContext context) {
			setObjectWrapper(wrapper);
			this.context = context;
		}

		public void putUnlistedModel(String key, TemplateModel model) {
			this.unlistedModels.put(key, model);
		}

		public TemplateModel get(String key) throws TemplateModelException {
			TemplateModel model = super.get(key);
			if (model != null) {
				return model;
			}

			model = (TemplateModel) this.unlistedModels.get(key);
			if (model != null) {
				return model;
			}

			Object obj = this.context.getAttribute(key);
			if (obj != null) {
				return wrap(obj);
			}

			return wrap(null);
		}

	}

}
