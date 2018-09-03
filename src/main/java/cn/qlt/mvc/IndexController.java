package cn.qlt.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.qlt.domain.Evaluation1;
import cn.qlt.service.DictService;
import cn.qlt.service.UserService;
import cn.qlt.utils.web.Auth;
import cn.qlt.utils.web.AuthUtil;

@Controller
public class IndexController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private DictService dictService;
	
	@GetMapping(value={"/","/index.html"})
	public String index(){
		return "/index.ftl";
	}
	
	@Auth(role="master")
	@GetMapping(value={"/users.html"})
	public String userlist(){
		return "/users.ftl";
	}
	@Auth(role="master")
	@GetMapping("/roles.html")
	public String rolelist(ModelMap map){
		map.put("roles", userService.findAllRole());
		return "/roles.ftl";
	}
	
	@Auth(role="master")
	@GetMapping("/dict.html")
	public String dictlist(ModelMap map){
		map.put("dictType", dictService.getDictForType("type"));
		return "/dictEdit.ftl";
	}
	
	@Auth
	@GetMapping("/userInfo.html")
	public String userInfo() throws Exception{
		return "userInfo.ftl";
	}	
	
	@Auth
	@GetMapping("/topic/my.html")
	public String myTopic() {
		return "topic/list.ftl";
	}
}
