package cn.qlt.utils.web.freemarker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.qlt.domain.Dict;
import freemarker.ext.beans.StringModel;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateCollectionModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateModelIterator;

public class ClassFormat implements TemplateMethodModelEx{
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		
		SimpleSequence slist = (SimpleSequence) arguments.get(0);
		
		Map<String,String> m = new HashMap();
		
		for(int i = 0 ; i< slist.size(); i++){
			StringModel bean = (StringModel) slist.get(i);
			m.put("code", bean.get("id")+"");
			m.put("label", "["+((StringModel)bean.get("grade")).get("label")+"] "+((StringModel)bean.get("specialty")).get("label")+" - "+((StringModel)bean.get("classes")).get("label"));
		}
		
		try {
			return objectMapper.writeValueAsString(m);
		} catch (Exception e) {
			e.printStackTrace();
			return "{}";
		}
	}

}
