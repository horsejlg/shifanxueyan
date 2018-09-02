package cn.qlt.utils.web.freemarker;

import java.util.List;

import org.springframework.util.StringUtils;

import cn.qlt.utils.AvatarsUtil;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class AvatarsParser implements TemplateMethodModelEx {

	@Override
	public Object exec(List args) throws TemplateModelException {
		String id = args.get(0).toString();
		if(StringUtils.isEmpty(id)|| !id.matches("[a-zA-Z0-9]{24}")){
			return null;
		}
		return AvatarsUtil.getPath(id);
	}
	

}
