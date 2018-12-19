package cn.qlt.mvc;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.qlt.domain.Message;
import cn.qlt.service.MessageService;
import cn.qlt.utils.web.Auth;
import cn.qlt.utils.web.AuthUtil;

@RestController
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	@Auth
	@PostMapping("/console/message")
	public boolean saveMessage(@RequestBody Message message){
		message.setFroms(AuthUtil.getCurrentUser());
		message.setState(1);
		messageService.saveMessage(message);
		return true;
	}
	
	@Auth
	@GetMapping("/console/message/news")
	public List<Map<String,Object>> newMyMessages(){
		return messageService.findMessageByUser(AuthUtil.getCurrentUser());
	}
	
	@Auth
	@PostMapping("/console/message/state")
	public boolean stateMessage(String id, int state){
		return messageService.updateState(id, state);
	}
	
	@Auth
	@DeleteMapping("/console/message")
	public boolean removeMessage(String id){
		messageService.removeMessage(id);
		return true;
	}
}
