package test.cn.qlt;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.qlt.dao.RoleDao;
import cn.qlt.dao.TopicDao;
import cn.qlt.dao.UserDao;
import cn.qlt.domain.Role;
import cn.qlt.domain.Topic;
import cn.qlt.domain.TopicReply;
import cn.qlt.domain.TopicWork;
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

	@Autowired
	private TopicDao topicDao;

	@Test
	@Transactional
	public void testTopic() throws Exception {
		Topic topic = new Topic();

		User author = userService.login("administrator", "123456");

		topic.setAuthor(author);
		topic.setTitle("测试专题1");
		topic.setContent(
				"8月2日，我国第一条地方自主高铁济青高铁开始联调联试，计划12月20日正式通车。根据中国铁总官方消息，济青高铁济南东站至胶州北站于8月21-24日顺利完成联调联试、运行试验安排，期间最快跑出了385公里的时速(点击看视频)，相当于每秒超过100米，创下联调联试期间的新纪录，比设计时速350公里超出10％。");
		topic.setEndTime(new Date());
		topic.setLocation("教学楼1#202");
		//topic.setParticipants(participants);
		topic.setPromiseTime(new Date());
		//topic.setVisibleUsers(visibleUsers);

		topic = topicService.createTopic(topic);
		// 上面是测试新增
		
		Set<User> participants = new HashSet<User>();
		
		User user1 = addUser();
		User user2 = addUser2();
		
		participants.add(user1);
		
		
		int l = topicService.addParticipants(topic.getId(), participants);
		
		System.out.println("##################"+l);
		
		
		participants.add(user2);
		
		l = topicService.addParticipants(topic.getId(), participants);
		
		System.out.println("##################"+l);
		
		participants.remove(user1);
		System.out.println("@@@@"+participants.size());
		l = topicService.remvoceParticipants(topic.getId(), participants);
		
		System.out.println("##################"+l);

		/*PageInfo pageinfo = new PageInfo(1, 10);
		Map<String, String> par = new HashMap<>();

		PageResult topics = topicService.find(par, pageinfo);// 查询所有
		System.out.println(topics.getRows());*/
//
//		System.out.println("####################################################");
//
//		/*
//		 * par.clear(); par.put("author_id", author.getId()); topics =
//		 * topicService.find(par,pageinfo);//查询作者
//		 */
//		User v = userService.login("class", "123456");
//		par.clear();
//		par.put("visibleUsers", v.getId());
//		topics = topicService.find(par, pageinfo);// 可见范围
//		System.out.println(topics.getRows());

		// 上面是测试查询

		/*
		 * Topic topicNew = new Topic(); BeanUtils.copyProperties((Topic)
		 * topics.getRows().get(0), topicNew);
		 * 
		 * topicNew.setTitle("测试专题1-"+new
		 * Date().getTime());//这里用查出来的直接set会触发update
		 * 
		 * topicService.updateTopic(topicNew, author);
		 * 
		 * //下面的会报无权限 User u = new User(); u.setId("123");
		 * topicService.updateTopic(topicNew, u);
		 * 
		 * List loglist = topicService.getTopicLogByTopicId(topicNew.getId());
		 * System.out.println(loglist);//输出修改记录
		 * 
		 */
		// 测试修改

		/*TopicReply r = new TopicReply();
		r.setAuthor(author);
		r.setContent("测试回复测试回复测试回复测试回复测试回复测试回复测试回复测试回复测试回复");
		r.setTopicId(((Topic) topics.getRows().get(0)).getId());
		topicService.addTopicReply(r);

		par.clear();
		par.put("topicId", r.getTopicId());
		PageResult pageResult = topicService.findTopicReply(par, pageinfo);
		
		System.out.println(pageResult.getRows());
		
		topicService.deleteTopicReply(((TopicReply)pageResult.getRows().get(0)).getId());
		
		pageResult = topicService.findTopicReply(par, pageinfo);
		
		System.out.println(pageResult.getRows());*/
		
		//专题回复
		
		/*TopicWork tw = new TopicWork();
		tw.setAuthor(author);
		tw.setTopicId(((Topic) topics.getRows().get(0)).getId());
		tw.setUrl("D://上传的文件地址");
		topicService.addTopicWork(tw);
		
		TopicWork tw2 = new TopicWork();
		tw2.setAuthor(author);
		tw2.setTopicId(((Topic) topics.getRows().get(0)).getId());
		tw2.setUrl("D://上传的文件地址2");
		topicService.addTopicWork(tw2);
		
		List<TopicWork> list = topicService.getTopicWorkByTopicId(tw2.getTopicId());
		
		System.out.println(list);*/
		
		//专题作业
	}

	private User addUser() {
		Role assistant = roleDao.findSingle("code", "assistant");

		User user = new User();
		user.setLoginname("assistant");
		user.setNickName("辅导员测试");
		user.setPassword("123456");
		user.getRoles().add(assistant);
		user.encodedPassword();
		return userDao.save(user);
	}

	private User addUser2() {
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
