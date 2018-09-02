package cn.qlt.mvc;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.qlt.domain.Student;
import cn.qlt.domain.Topic;
import cn.qlt.domain.TopicLog;
import cn.qlt.domain.TopicWork;
import cn.qlt.domain.User;
import cn.qlt.service.StudentService;
import cn.qlt.service.TopicService;
import cn.qlt.utils.SQLUtils.PageInfo;
import cn.qlt.utils.SQLUtils.PageResult;
import cn.qlt.utils.web.AuthUtil;

@RestController
public class TopicController {
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private StudentService studentService;
	
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
	
	@DeleteMapping(value="/topic/{topicId}")
	public boolean deleteTopic(@PathVariable String topicId){
		topicService.deleteTopic(topicId);
		return true;
	}
	
	//修改日志
	@GetMapping(value="/topicLog/{id}")
	public List<TopicLog> gettopicLogByid(String id){
		return topicService.getTopicLogByTopicId(id);
	}
	
	//综合查询
	/** 
	 * 出3个列表:
	 * 	所有 (学生,老师) publish=1,visibleUsers=?(可不加,后面会自动加这个参数)
	 * 	我参与的 (学生,老师) publish=1,participants=?
	 * 	我发布的 (老师) author_id=?
	 * @param params
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@PostMapping(value="/topics/{page}/{pageSize}")
	public PageResult findTopic(Map<String,String> params,@PathVariable int page,@PathVariable int pageSize){
		PageInfo pageinfo = new PageInfo(page, pageSize);
		User user = AuthUtil.getCurrentUser();
		Student t = studentService.getStudentById(user.getId());
		if(null!=t){//如果是学生 那就使用可见范围这个限制
			params.put("visibleUsers", user.getId());
		}
		return topicService.find(params, pageinfo);
	}
	
	/**
	 * 获取专题评论
	 * @param params
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@PostMapping(value="/topicReply/{page}/{pageSize}")
	public PageResult findTopicReply(Map<String,String> params,@PathVariable int page,@PathVariable int pageSize){
		PageInfo pageinfo = new PageInfo(page, pageSize);
		return topicService.findTopicReply(params, pageinfo);
	}
	
	@DeleteMapping(value="/topicReply/{topicReplyId}")
	public boolean deleteTopicReply(@PathVariable String topicReplyId){
		topicService.deleteTopicReply(topicReplyId);
		return true;
	}
	
	/**
	 * 这里没做判断,随便交作业,多的也显示不出来
	 * @param topicWork
	 */
	@PostMapping(value="/topicWork")
	public void addTopicWork(TopicWork topicWork){
		topicService.addTopicWork(topicWork);
	}
	
	@GetMapping(value="/topicWork/{topicId}")
	public List<TopicWork> getTopicWorkByTopicId(@PathVariable String topicId){
		return topicService.getTopicWorkByTopicId(topicId);
	}

}
