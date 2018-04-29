package cn.qlt.utils.web.freemarker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.google.gson.Gson;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import freemarker.template.Version;

public class FreemarkerJsonParser implements TemplateMethodModelEx{
	
	BeansWrapper beansWrapper = new BeansWrapper(new Version(2,3,22));
	
    @SuppressWarnings("all")
	@Override
    public Object exec(List args) throws TemplateModelException {
			String string = args.get(0).toString();
			if(StringUtils.isEmpty(string)|| !string.contains("{")){
				return null;
			}
			Class type = string.startsWith("[") ? HashMap[].class : HashMap.class;
			Gson gson = new Gson();
			Object json = gson.fromJson(string, type);
			if(type.equals(HashMap.class)){
				return json;
			}else{
				List list = new ArrayList();
				for(Map map: (HashMap[])json){
					list.add(map);
				}
				return list;
			}
    }
}
