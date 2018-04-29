package cn.qlt.utils.web.freemarker; 

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import freemarker.cache.TemplateCache;
import freemarker.core.Environment;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author badqiu
 */
public class ExtendsDirective implements TemplateDirectiveModel {
	
	@Value("${file.template.path}")
	private String fileTemplatePath;

	public final static String DIRECTIVE_NAME = "extends";
	
	@SuppressWarnings("rawtypes")
	public void execute(Environment env,
            Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body) throws TemplateException, IOException {
		
		String name = DirectiveUtils.getRequiredParam(params, "name");
		String encoding = DirectiveUtils.getParam(params, "encoding",null);
		String includeTemplateName = TemplateCache.getFullTemplatePath(env, getTemplatePath(env), name);
		if(includeTemplateName.startsWith("filepath:")){
			env.include(new Template(includeTemplateName, new FileReader(includeTemplateName.replaceFirst("filepath:", fileTemplatePath)), env.getConfiguration(), "utf-8"));
		}else{
			env.include(includeTemplateName, encoding, true);
		}
	}

	private String getTemplatePath(Environment env) {
		String templateName = env.getTemplate().getName();
		if(templateName.startsWith("filepath:"))
			return templateName.replaceFirst("filepath:", fileTemplatePath);
		else
			return templateName.lastIndexOf('/') == -1 ? "" : templateName.substring(0, templateName.lastIndexOf('/') + 1);
	}

}
