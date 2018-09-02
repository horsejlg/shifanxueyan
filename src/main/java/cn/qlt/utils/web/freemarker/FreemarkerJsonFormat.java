package cn.qlt.utils.web.freemarker;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.StringModel;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.Version;

public class FreemarkerJsonFormat implements TemplateMethodModelEx{
	
	BeansWrapper beansWrapper = new BeansWrapper(new Version(2,3,22));
	ObjectMapper objectMapper = new ObjectMapper();
	
    @SuppressWarnings("all")
	@Override
    public Object exec(List args) throws TemplateModelException {
    	Object obj = args.get(0);
			if(obj ==null){
				return "{}";
			}
			if(obj instanceof StringModel){
				StringModel sm = (StringModel) obj;
				obj = sm.getWrappedObject();
			}
			if(obj instanceof SimpleSequence){
				SimpleSequence ss = (SimpleSequence) obj;
				
				List list = new ArrayList();
				for(int i = 0 ; i< ss.size(); i++){
					StringModel bean = (StringModel) ss.get(i);
					list.add(bean.getWrappedObject());
				}
				try {
					return objectMapper.writeValueAsString(list);
				} catch (JsonProcessingException e) {
					throw new RuntimeException(e);
				}
			}else{
				try {
					return objectMapper.writeValueAsString(obj);
				} catch (JsonProcessingException e) {
					throw new RuntimeException(e);
				}
			}
    }
}
