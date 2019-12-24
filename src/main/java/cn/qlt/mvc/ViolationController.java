package cn.qlt.mvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.qlt.service.ViolationService;
import cn.qlt.utils.SQLUtils;
import cn.qlt.utils.SQLUtils.PageInfo;
import cn.qlt.utils.SQLUtils.PageResult;
import cn.qlt.utils.web.Auth;
import cn.qlt.utils.web.RequestUtil;

@Controller
public class ViolationController {

	@Autowired
	private ViolationService violationService;
	
	@Auth
	@PostMapping("/violations/{type}")
	@ResponseBody
	public PageResult listViolation(@PathVariable("type") String type,HttpServletRequest request) {
		Map<String, String> params = RequestUtil.getParams(request);
		PageInfo pageInfo = SQLUtils.getPageInfo(params);
		switch (type) {
		case "NotPass":
			return violationService.findNotPass(params, pageInfo);
		case "GeneralDiscipline":
			return violationService.findGeneralDiscipline(params, pageInfo);
		case "SeriousDisciplinary":
			return violationService.findSeriousDisciplinary(params, pageInfo);
		default:
			return new PageResult();
		}
	}
	
	@Auth
	@PostMapping("/show/violation/{type}")
	public String showViolations(@PathVariable("type") String type) {
		return "/violation/".concat(type).concat(".ftl");
	}
	
}
