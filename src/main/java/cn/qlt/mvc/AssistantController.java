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

import cn.qlt.domain.Awards;
import cn.qlt.domain.Sociogram;
import cn.qlt.domain.Assistant;
import cn.qlt.domain.User;
import cn.qlt.service.AssistantService;
import cn.qlt.utils.web.Auth;

@Controller
public class AssistantController {
	
	@Autowired
	private AssistantService assistantService;

	@GetMapping("/assistant/show/{id}.html")
	public String showAssistant(@PathVariable("id")String id, ModelMap map){
		map.put("assistant", assistantService.loadFullAssistant(id));
		return "assistant/show.ftl";
	}
	
	@GetMapping("/assistant/edit/{id}.html")
	public String editAssistant(@PathVariable("id")String id, ModelMap map){
		map.put("assistant", assistantService.loadFullAssistant(id));
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
	@PostMapping(value="/assistant/sociogram/{id}")
	@ResponseBody
	public String saveSociogram(@RequestBody Sociogram sociogram, @PathVariable String id){
		sociogram.setUser(new User(id));
		assistantService.saveSociogram(sociogram);
		return sociogram.getId();
	}
	
	@Auth
	@DeleteMapping(value="/assistant/sociogram/{id}")
	@ResponseBody
	public boolean deleteSociogram(@PathVariable String id){
		assistantService.deleteSociogram(id);
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
