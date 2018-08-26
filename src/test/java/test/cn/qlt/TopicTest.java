package test.cn.qlt;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.qlt.dao.RoleDao;
import cn.qlt.dao.UserDao;
import cn.qlt.domain.Role;
import cn.qlt.domain.Topic;
import cn.qlt.domain.User;
import cn.qlt.service.TopicService;
import cn.qlt.service.UserService;
import cn.qlt.utils.SQLUtils.PageInfo;
import cn.qlt.utils.SQLUtils.PageResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/conf-spring/spring-db.xml" })
@Transactional
public class TopicTest {
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Test
	@Transactional
	public void testTopic() throws Exception{
		Topic topic = new Topic();
		
		User author = userService.login("administrator", "123456");
		List<User> participants = new ArrayList<User>();
		participants.add(addUser());
		
		List<User> visibleUsers = new ArrayList<User>();
		visibleUsers.add(addUser2());
		
		topic.setAuthor(author);
		topic.setTitle("测试专题1");
		topic.setContent("8月2日，我国第一条地方自主高铁济青高铁开始联调联试，计划12月20日正式通车。根据中国铁总官方消息，济青高铁济南东站至胶州北站于8月21-24日顺利完成联调联试、运行试验安排，期间最快跑出了385公里的时速(点击看视频)，相当于每秒超过100米，创下联调联试期间的新纪录，比设计时速350公里超出10％。");
		topic.setEndTime(new Date());
		topic.setLocation("教学楼1#202");
		topic.setParticipants(participants);
		topic.setPromiseTime(new Date());
		topic.setVisibleUsers(visibleUsers);
		
		topicService.createTopic(topic);
		
		PageInfo pageinfo = new PageInfo(1, 10);
		Map<String, String> par = new HashMap<>();
		
		PageResult topics = topicService.find(par,pageinfo);//查询所有
		System.out.println(topics.getRows());
		
		System.out.println("####################################################");
		
		par.clear();
		par.put("author_id", author.getId());
		topics = topicService.find(par,pageinfo);//查询作者
		System.out.println(topics.getRows());
	}
	
	private User addUser(){
		Role assistant = roleDao.findSingle("code", "assistant");
		
		User user = new User();
		user.setLoginname("assistant");
		user.setNickName("辅导员测试");
		user.setPassword("123456");
		user.getRoles().add(assistant);
		user.encodedPassword();
		return userDao.save(user);
	}
	
	private User addUser2(){
		Role classs = roleDao.findSingle("code", "class");
		
		User user = new User();
		user.setLoginname("class");
		user.setNickName("班级管理员");
		user.setPassword("123456");
		user.getRoles().add(classs);
		user.encodedPassword();
		return userDao.save(user);
	}

}
