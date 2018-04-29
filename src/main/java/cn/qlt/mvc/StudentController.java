package cn.qlt.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cn.qlt.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;

	@GetMapping("/student/show/{id}.html")
	public String showStudent(@PathVariable("id")String id, ModelMap map){
		map.put("student", studentService.loadFullStudent(id));
		return "student/show.ftl";
	}
}
