package cn.qlt.mvc;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.qlt.domain.Awards;
import cn.qlt.domain.Sociogram;
import cn.qlt.domain.Student;
import cn.qlt.domain.User;
import cn.qlt.service.StudentService;
import cn.qlt.utils.SQLUtils;
import cn.qlt.utils.SQLUtils.PageResult;
import cn.qlt.utils.web.Auth;
import cn.qlt.utils.web.RequestUtil;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("student/tree/list")
	public String list(){
		return "student/tree.ftl";
	}

	@GetMapping("/student/show/{id}.html")
	public String showStudent(@PathVariable("id")String id, ModelMap map){
		map.put("student", studentService.loadFullStudent(id));
		return "student/show.ftl";
	}
	
	@Auth
	@GetMapping("/student/edit/{id}.html")
	public String editStudent(@PathVariable("id")String id, ModelMap map){
		map.put("student", studentService.loadFullStudent(id));
		return "student/edit.ftl";
	}
	
	@Auth
	@PostMapping(value="/student")
	@ResponseBody
	public String saveStudent(@RequestBody Student student, ModelMap map){
		studentService.saveStudent(student);
		return student.getId();
	}
	
	@Auth
	@PostMapping(value="/student/sociogram/{id}")
	@ResponseBody
	public String saveSociogram(@RequestBody Sociogram sociogram, @PathVariable String id){
		sociogram.setUser(new User(id));
		studentService.saveSociogram(sociogram);
		return sociogram.getId();
	}
	
	@Auth
	@DeleteMapping(value="/student/sociogram/{id}")
	@ResponseBody
	public boolean deleteSociogram(@PathVariable String id){
		studentService.deleteSociogram(id);
		return true;
	}
	
	@Auth
	@PostMapping(value="/student/awards/{id}")
	@ResponseBody
	public boolean saveAwards(@RequestBody Awards awards, @PathVariable String id){
		awards.setUser(new User(id));
		studentService.saveAwards(awards);
		return true;
	}
	
	@Auth
	@DeleteMapping(value="/student/awards/{id}")
	@ResponseBody
	public boolean deleteAwards(@PathVariable String id){
		studentService.deleteAwards(id);
		return true;
	}
	
	@Auth(role="assistant")
	@PostMapping(value="/students")
	public PageResult findUser(HttpServletRequest request){
		Map<String, String> params = RequestUtil.getParams(request);
		return studentService.find(params, SQLUtils.getPageInfo(params));
	}
}
