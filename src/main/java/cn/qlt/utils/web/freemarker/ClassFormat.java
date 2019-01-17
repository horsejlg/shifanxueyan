package cn.qlt.utils.web.freemarker;

import java.util.ArrayList;
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
		
		List<Map<String,String>> l = new ArrayList<>();
		
		System.out.println(slist);
		
		System.out.println("###################");
		
		for(int i = 0 ; i< slist.size(); i++){
			StringModel bean = (StringModel) slist.get(i);
			Map<String,String> m = new HashMap<>();
			m.put("code", bean.get("id")+"");
			m.put("label", "["+((StringModel)bean.get("grade")).get("label")+"] "+((StringModel)bean.get("specialty")).get("label")+" - "+((StringModel)bean.get("classes")).get("label"));
			l.add(m);
		}
		
		System.out.println(l);
		
		try {
			return objectMapper.writeValueAsString(l);
		} catch (Exception e) {
			e.printStackTrace();
			return "{}";
		}
	}

}
