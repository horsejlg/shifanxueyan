package cn.qlt.mvc;

import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.qlt.domain.Dict;
import cn.qlt.domain.Evaluation;
import cn.qlt.domain.Evaluation1;
import cn.qlt.domain.Evaluation2;
import cn.qlt.domain.Role;
import cn.qlt.domain.User;
import cn.qlt.service.DictService;
import cn.qlt.service.EvaluationService;
import cn.qlt.service.UserService;
import cn.qlt.utils.SQLUtils;
import cn.qlt.utils.SQLUtils.PageResult;
import cn.qlt.utils.web.Auth;
import cn.qlt.utils.web.AuthUtil;
import cn.qlt.utils.web.RequestUtil;

@Controller
public class EvaluationController {

	@Autowired
	private EvaluationService evaluationService;

	@Autowired
	private DictService dictService;

	@Autowired
	private UserService userService;
	
	@Value("${def.evaluation.class}")
	private String defEvalName;
	
/*	@Auth
	@GetMapping("/meEvalutions.html")
	public String meEvalutions(ModelMap map){
		map.put("status", 0);
		return "evaluation.ftl";
	}

	@Auth
	@GetMapping("/evalutions/class.html")
	public String classEvalutions(ModelMap map){
		map.put("status", 1);
		return "evaluation.ftl";
	}
	
	@Auth
	@GetMapping("/evalutions/group.html")
	public String groupEvalutions(ModelMap map){
		map.put("status", 2);
		return "evaluation.ftl";
	}*/

	@Auth
	@GetMapping("/{year}/evaluation.html")
	public String evaluation1(ModelMap map, @PathVariable("year")String year) throws Exception {
		User currentUser = AuthUtil.getCurrentUser();
		Evaluation value = evaluationService.findByUser(currentUser.getId(), year, "Evaluation1");
		if(value!=null){
			return sendToPage(map, currentUser, value);
		}
		Class<Evaluation> evalClass = (Class<Evaluation>) ClassLoader.getSystemClassLoader().loadClass(defEvalName);
		value = evalClass.newInstance();
		value.setAuthor(currentUser);
		value.setYear(dictService.findDict(year));
		map.put("evaluation", value);
		map.put("edit", true);
		return String.format("/evaluation/%s.ftl", evalClass.getSimpleName().toLowerCase());
	}
	
	@Auth
	@ResponseBody
	@GetMapping("/evaluation/index/{entityName}/{colname}/{grade}/{year}/{specialty}/{classes}/{number}")
	public Long getIndex(@PathVariable String entityName,@PathVariable String colname,@PathVariable String grade, @PathVariable String year, @PathVariable String specialty, @PathVariable String classes, @PathVariable Float number){
		return 1+evaluationService.ofIndex(entityName, colname, year, grade, specialty, number, classes);
	}

	@Auth
	@GetMapping("/evaluation/{id}.html")
	public String openEevaluation(@PathVariable String id, ModelMap map) {
		User user = AuthUtil.getCurrentUser();
		Evaluation value = evaluationService.loadEvaluation(id);
		return sendToPage(map, user, value);
	}

	private String sendToPage(ModelMap map, User user, Evaluation value) {
		String path = String.format("/evaluation/%s.ftl", value.getClass().getSimpleName().toLowerCase());
		map.put("evaluation", value);
		if(value.getStatus() ==3){
			map.put("edit", false);
		}else if (value.getStatus() ==0 && user.getId().equals(value.getAuthor().getId())) {
			map.put("edit", true);
		} else {
			Set<Role> roles = user.getRoles();
			for (Role role : roles) {
				switch (role.getCode()) {
				case "teacher":
					if (value.getStatus() ==2){
						map.put("edit", true);
						return path;
					}
					break;
				case "class":
					if (value.getStatus() ==1 && value.getAuthor().getClasses().getCode().equals(user.getClasses().getCode())) {
						map.put("edit", true);
						return path;
					}
					break;
				default:
					break;
				}
			}
			if (map.containsKey("edit")) {
				map.put("edit", false);
			}
		}
		return path;
	}

	@Auth
	@PostMapping("/evaluation1")
	@ResponseBody
	public Evaluation saveEvaluation1(@RequestBody Evaluation1 eval) {
		return saveEvaluation(eval);
	}
	
	@Auth
	@PostMapping("/evaluation2")
	@ResponseBody
	public Evaluation saveEvaluation2(@RequestBody Evaluation2 eval) {
		return saveEvaluation(eval);
	}

	private Evaluation saveEvaluation(Evaluation eval) {
		if("".equals(eval.getId())){
			eval.setId(null);
			if (StringUtils.isEmpty(eval.getTitle())) {
				eval.setTitle("齐鲁师范学院 教师教育学院" + dictService.findDict(eval.getYear().getCode()).getLabel() + "学年学生综合素质测评表——"
						+ userService.loadUser(eval.getAuthor().getId()).getNickName());
			}
			evaluationService.save(eval);
			return eval;
		}else{
			Evaluation old = evaluationService.loadEvaluation(eval.getId());
			switch (old.getStatus()) {
			case 0:
				BeanUtils.copyProperties(eval, old, "id","year","title","createTime","author","groupEvaluation","groupEvaluationDate");
				break;
			case 1:
				BeanUtils.copyProperties(eval, old, "id","year","title","createTime","author","groupEvaluation","groupEvaluationDate","selfEvaluation","selfEvaluationDate");
				break;
			case 2:
				BeanUtils.copyProperties(eval, old, "id","year","title","createTime","author","groupEvaluationDate","selfEvaluation","selfEvaluationDate");
				if(old instanceof Evaluation1){
				if(((Evaluation1)old).getGroupEvaluationDate()==null)
					((Evaluation1)old).setGroupEvaluationDate(new Date());
				}
				break;
			default:
				return old;
			}
			if(old instanceof Evaluation1){
				((Evaluation1)old).setBaseEvaluationLevel(new Float(((Evaluation1)old).getBaseEvaluationSorce()/10).intValue());
			}
			if(old instanceof Evaluation2){
				((Evaluation2)old).setBaseEvaluationLevel(new Float(((Evaluation1)old).getBaseEvaluationSorce()/10).intValue());
			}
			old.setGsIndex(eval.getGsIndex());
			evaluationService.save(old);
			return old;
		}
	}
	
	@Auth
	@GetMapping("/evaluations/{type}.html")
	public String evaluationList(@PathVariable("type")String type, ModelMap map){
		map.put("type", type);
		return "evaluationlist.ftl";
	}
	
	@Auth
	@PostMapping("/evaluations/{type}")
	@ResponseBody
	public PageResult findEvaluations(@PathVariable("type") String type,HttpServletRequest request){
		Map<String, String> params = RequestUtil.getParams(request);
		if(!params.containsKey("table"))
			params.put("table", "Evaluation");
		User user = AuthUtil.getCurrentUser();
		switch (type) {
		case "my":
			params.put("authorId", user.getId());
			break;
		case "class":
			params.put("classes", user.getClasses().getCode());
		default:
			params.put("issubmit","1");
			break;
		}
		return evaluationService.find(params, SQLUtils.getPageInfo(params));
	}
	
	@GetMapping("/evaluation/download/excel/{type}/{grade}/{classes}/{year}")
	public void downloadExcel(@PathVariable String type, @PathVariable String grade, @PathVariable String classes, @PathVariable String year, HttpServletRequest request,HttpServletResponse response) throws Exception{
		response.reset();
        response.setContentType("application/x-download");// 设置为下载application/x-download   
        response.addHeader("content-type ","application/x-msdownload");   
        response.setContentType("application/octet-stream");  
        Dict dictYear = dictService.findDict(year);
		String name = Evaluation.showName(type);
		Dict dictGrade = dictService.findDict(grade);
		Dict dictClasses = dictService.findDict(classes);
		String filename = String.format("%s_%s_%s_%s.xls",
        		name, 
        		dictYear.getLabel(),
        		dictGrade.getLabel(),
        		dictClasses.getLabel());
		if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {  
		    filename = URLEncoder.encode(filename, "UTF-8");  
		} else {  
		    filename = new String(filename.getBytes("UTF-8"), "ISO8859-1"); 
		}
		response.setHeader("content-disposition", "attachment; filename=" + filename);
        evaluationService.writeExcal(type, dictGrade, dictClasses, dictYear,response.getOutputStream());
	}
}
