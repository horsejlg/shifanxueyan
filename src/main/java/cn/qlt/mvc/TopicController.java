package cn.qlt.mvc;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.qlt.domain.Topic;
import cn.qlt.domain.TopicLog;
import cn.qlt.domain.User;
import cn.qlt.service.TopicService;
import cn.qlt.utils.SQLUtils.PageInfo;
import cn.qlt.utils.SQLUtils.PageResult;
import cn.qlt.utils.web.AuthUtil;

@RestController
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	//新建
	@PostMapping(value="/topic")
	public boolean createTopic(Topic topic) throws Exception{
		topicService.createTopic(topic);
		return true;
	}
	
	//修改 只有作者和参与人员可修改
	@PutMapping(value="/topic")
	public boolean updateTopic(Topic topic) throws Exception{
		User user = AuthUtil.getCurrentUser();
		
		topicService.updateTopic(topic, user);
		return true;
	}
	
	@GetMapping(value="/topic/{id}")
	public Topic getTopicByid(String id){
		return topicService.getTopicByid(id);
	}
	
	//修改日志
	@GetMapping(value="/topicLog/{id}")
	public List<TopicLog> gettopicLogByid(String id){
		return topicService.getTopicLogByTopicId(id);
	}
	
	//综合查询
	@PostMapping(value="/topics/{page}/{pageSize}")
	public PageResult findTopic(Map<String,String> params,@PathVariable int page,@PathVariable int pageSize){
		PageInfo pageinfo = new PageInfo(page, pageSize);
		return topicService.find(params, pageinfo);
	}
	

}
