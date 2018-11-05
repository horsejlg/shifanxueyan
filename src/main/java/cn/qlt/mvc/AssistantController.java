package cn.qlt.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.qlt.domain.Assistant;
import cn.qlt.domain.Awards;
import cn.qlt.domain.ClassTeam;
import cn.qlt.domain.Dict;
import cn.qlt.domain.User;
import cn.qlt.service.AssistantService;
import cn.qlt.utils.web.Auth;

@Controller
public class AssistantController {
	
	@Autowired
	private AssistantService assistantService;

	@GetMapping("/assistant/show/{id}.html")
	public String showAssistant(@PathVariable("id")String id, ModelMap map){
		map.put("assistant", assistantService.loadAssistantById(id));
		return "assistant/show.ftl";
	}
	
	@GetMapping("/assistant/edit/{id}.html")
	public String editAssistant(@PathVariable("id")String id, ModelMap map){
		map.put("assistant", assistantService.loadAssistantById(id));
		return "assistant/edit.ftl";
	}
	
	@Auth
	@PostMapping(value="/assistant")
	@ResponseBody
	public String saveAssistant(@RequestBody Assistant assistant, ModelMap map){
		assistantService.saveAssistant(assistant);
		return assistant.getId();
	}
	
	@Auth
	@PostMapping(value="/assistant/grade/{id}")
	@ResponseBody
	public void saveGrade(@RequestBody Dict grade, @PathVariable String id){
		assistantService.saveGrade(id,grade);
	}
	
	@Auth
	@PostMapping(value="/assistant/grade/{id}/del")
	@ResponseBody
	public void deleteGrade(@RequestBody Dict grade,@PathVariable String id){
		assistantService.deleteGrade(id,grade);
	}
	
	@Auth
	@PostMapping(value="/assistant/classTeam/{id}")
	@ResponseBody
	public void saveClassTeam(@RequestBody ClassTeam classTeam, @PathVariable String id){
		assistantService.saveClassTeam(id,classTeam);
	}
	
	@Auth
	@PostMapping(value="/assistant/classTeam/{id}/del")
	@ResponseBody
	public boolean deleteClassTeam(@RequestBody ClassTeam classTeam,@PathVariable String id){
		assistantService.deleteClassTeam(id,classTeam);
		return true;
	}
	
	@Auth
	@PostMapping(value="/assistant/awards/{id}")
	@ResponseBody
	public boolean saveAwards(@RequestBody Awards awards, @PathVariable String id){
		awards.setUser(new User(id));
		assistantService.saveAwards(awards);
		return true;
	}
	
	@Auth
	@DeleteMapping(value="/assistant/awards/{id}")
	@ResponseBody
	public boolean deleteAwards(@PathVariable String id){
		assistantService.deleteAwards(id);
		return true;
	}
}
