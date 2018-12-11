package cn.qlt.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import cn.qlt.domain.Topic;
import cn.qlt.domain.User;
import cn.qlt.domain.dto.TopicEdit;
import cn.qlt.service.DictService;
import cn.qlt.service.TopicService;
import cn.qlt.service.UserService;
import cn.qlt.utils.web.Auth;
import cn.qlt.utils.web.AuthUtil;

@Controller
public class IndexController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private DictService dictService;
	
	@Autowired
	private TopicService topicService;
	
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
	public String userInfo(ModelMap map) throws Exception{
		map.put("cuser", userService.loadUser(AuthUtil.getCurrentUser().getId()));
		return "userInfo.ftl";
	}
	
	@Auth
	@GetMapping("/topic/my.html")
	public String myTopic() {
		return "topic/tree.ftl";
	}
	
	@Auth
	@GetMapping("/topic/edit/{id}.html")
	public String editTopic(@PathVariable String id,ModelMap map) {
		User user = AuthUtil.getCurrentUser();
		TopicEdit topicEdit = topicService.getTopicEdit(id, user);
		map.put("topic", topicEdit.getTopic());
		map.put("permissions", topicEdit.getPermissions());
		return "topic/edit.ftl";
	}
	
	@Auth(role="assistant")
	@GetMapping("/topic/create.html")
	public String creatTopic(ModelMap map) {
		map.put("topic", new Topic());
		map.put("permissions", 2);
		return "topic/edit.ftl";
	}
}
