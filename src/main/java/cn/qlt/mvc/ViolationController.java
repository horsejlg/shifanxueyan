package cn.qlt.mvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.qlt.domain.GeneralDiscipline;
import cn.qlt.domain.NotPass;
import cn.qlt.domain.SeriousDisciplinary;
import cn.qlt.domain.Student;
import cn.qlt.domain.Violation;
import cn.qlt.service.StudentService;
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
	
	@Autowired
	private StudentService studentService;
	
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
	@GetMapping("/show/violation/{type}.html")
	public String showViolations(@PathVariable("type") String type) {
		return "/violation/".concat(type).concat(".ftl");
	}
	
	public String editViolation(ModelMap map, @PathVariable("type") String type, HttpServletRequest request){
	    String studentId = request.getParameter("studentId");
	    if(!StringUtils.isEmpty(studentId)) {
		Student student = studentService.getStudentById(studentId);
		if(student!=null) {
		    map.put("student", student);
		}else {
		    throw new RuntimeException("没有找到指定的学生！");
		}
	    }else {
		String id = request.getParameter("id");
		if(!StringUtils.isEmpty(id)) {
		    Violation violation = violationService.getViolation(id);
		    if(violation!=null) {
			map.put("violation", violation);
		    }else {
			throw new RuntimeException("没有找到指定的违规记录！");
		    }
		}else {
		    throw new RuntimeException("没有指定违规记录ID");
		}
	    }
	    return "/violation/set".concat(type).concat(".ftl");
	}
	
	@Auth
	@PostMapping("/violation/notPass")
	public String saveNotPass(@RequestBody NotPass notPass) {
		violationService.saveViolation(notPass);
		return "true";
	}
	
	@Auth
	@PostMapping("/violation/seriousDisciplinary")
	public String saveNotPass(@RequestBody SeriousDisciplinary seriousDisciplinary) {
		violationService.saveViolation(seriousDisciplinary);
		return "true";
	}
	
	@Auth
	@PostMapping("/violation/generalDiscipline")
	public String saveNotPass(@RequestBody GeneralDiscipline generalDiscipline) {
		violationService.saveViolation(generalDiscipline);
		return "true";
	}
	
}
