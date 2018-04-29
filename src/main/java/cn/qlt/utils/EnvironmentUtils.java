package cn.qlt.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import freemarker.template.SimpleHash;

@Component
public class EnvironmentUtils implements ApplicationContextAware {

	private ApplicationContext applicationContext;
	
	@Value("basepath")
	private String basepath;
	
	
	
	private static ThreadLocal<Enviroment> threadLocal = new ThreadLocal<Enviroment>();
	
	public static Enviroment currentEnvironment(){
		return threadLocal.get();
	}

	public static void clear(){
		threadLocal.remove();
	}
	
	public void createEnvironment(){
		Enviroment value = new Enviroment(applicationContext,basepath);
		threadLocal.set(value);
	}
	
	@PostConstruct
	public void init(){
		PropertyResourceConfigurer bean = applicationContext.getBean(PropertyResourceConfigurer.class);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	public static class Enviroment{
		private ApplicationContext applicationContext; 
		private String basepath;
		private String template;
		private SimpleHash session = null;
		private HttpServletRequest request;
		
		public SimpleHash getSession() {
			if(session!=null)
				return session;
			else
				return new SimpleHash();
		}

		public void setSession(HttpSession session) {
			this.session = new SimpleHash();
			Enumeration<String> names = session.getAttributeNames();
			while(names.hasMoreElements()){
				String key = names.nextElement();
				this.session.put(key, session.getAttribute(key));
			}
		}
		
		public HttpServletRequest getRequest() {
			return request;
		}

		public void setRequest(HttpServletRequest request) {
			this.request = request;
		}



		private String base;
		private String servicePath;
		
		public String getBase() {
			return base;
		}

		public void setBase(HttpServletRequest request) {
			this.base = request.getContextPath();
		}

		public String getServicePath() {
			return servicePath;
		}

		public void setServicePath(String servicePath) {
			this.servicePath = servicePath;
		}
		private Map<String, Object> attributer = new HashMap<String, Object>();
			
		
		public Enviroment(ApplicationContext applicationContext,
				String basepath) {
			super();
			this.applicationContext = applicationContext;
			this.basepath = basepath;
		}

		public <T> T getBean(String name, Class<T> t){
			return applicationContext.getBean(name, t);
		}
		
		public <T> T getBean(Class<T> t){
			return applicationContext.getBean(t);
		}
		
		public Object getAttributer(String key){
			return attributer.get(key);
		}
		
		public void setAttributer(String key, Object value){
			attributer.put(key, value);
		}
		
		public String getBasepath() {
			return basepath;
		}
		
		public String getTemplate() {
			return template;
		}
		public void setTemplate(String template) {
			this.template = template;
		}
		
		
	}
}
