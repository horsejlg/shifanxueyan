package cn.qlt.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.qlt.dao.ClassTeamDao;
import cn.qlt.dao.StudentDao;
import cn.qlt.dao.TopicDao;
import cn.qlt.dao.TopicLogDao;
import cn.qlt.dao.TopicReplyDao;
import cn.qlt.dao.TopicWorkDao;
import cn.qlt.dao.UserDao;
import cn.qlt.domain.Dict;
import cn.qlt.domain.Topic;
import cn.qlt.domain.TopicLog;
import cn.qlt.domain.TopicReply;
import cn.qlt.domain.TopicWork;
import cn.qlt.domain.User;
import cn.qlt.domain.dto.TopicEdit;
import cn.qlt.utils.CompareUtils;
import cn.qlt.utils.SQLUtils.PageInfo;
import cn.qlt.utils.SQLUtils.PageResult;
import cn.qlt.utils.web.AuthUtil;
import cn.qlt.utils.web.BusinessException;

/**
 * @author zp
 * 主题
 */
@Service
public class TopicService {

	@Autowired
	private TopicDao topicDao;
	
	@Autowired
	private TopicLogDao topicLogDao;
	
	@Autowired
	private TopicReplyDao topicReplyDao;
	
	@Autowired
	private TopicWorkDao topicWorkDao;
	
	@Autowired
	private ClassTeamDao classTeamDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public int addVisibleUsersByClass(User opUser,String topicId,Dict specialty,Dict grade,Dict classes) throws Exception {
		List<User> users = userDao.find("from User u where u.specialty = ? and u.grade = ? and u.classes = ?", specialty,grade,classes);
		
		return addVisibleUsers(opUser, topicId, new HashSet(users));
	}
	
	@Transactional
	public int addVisibleUsers(User opUser,String topicId,Set<User> users) throws Exception {
		Topic topic = topicDao.load(topicId);
		if(null!=topic && topic.getAuthor().equals(opUser)) {
			if(null==topic.getVisibleUsers()) {
				topic.setVisibleUsers(users);
			}else {
				topic.getVisibleUsers().addAll(users);
			}
			
			topicDao.save(topic);
			return topic.getVisibleUsers().size();
		}
		throw new Exception("专题不存在或您没有操作权限");
	}
	
	/**
	 * @param opUser
	 * @param topicId
	 * @param users
	 * @param isSynchronous 是否同时在参与人员里面也删这些人
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public int remvoceVisibleUsers(User opUser,String topicId,Set<User> users, boolean isSynchronous) throws Exception {
		Topic topic = topicDao.load(topicId);
		if(null!=topic && topic.getAuthor().equals(opUser)) {
			
			if(null!=topic.getVisibleUsers()) {
				topic.getVisibleUsers().removeAll(users);
				topicDao.save(topic);
				
				if(isSynchronous) {
					remvoceParticipants(opUser, topicId, users,false);
				}
				
				return topic.getVisibleUsers().size();
			}
			
		}
		throw new Exception("专题不存在或您没有操作权限");
	}
	
	@Transactional
	public int addParticipantsByClass(User opUser,String topicId,Dict specialty,Dict grade,Dict classes) throws Exception {
		List<User> users = userDao.find("from User u where u.specialty = ? and u.grade = ? and u.classes = ?", specialty,grade,classes);
		
		return addParticipants(opUser, topicId, new HashSet(users));
	}
	
	@Transactional
	public int addParticipants(User opUser,String topicId,Set<User> users) throws Exception {
		Topic topic = topicDao.load(topicId);
		
		if(null!=topic && topic.getAuthor().equals(opUser)) {
			if(null==topic.getParticipants()) {
				topic.setParticipants(users);
			}else {
				topic.getParticipants().addAll(users);
			}
			
			//把参与人员加到可见人员里面
			addVisibleUsers(opUser, topicId, users);
			topicDao.save(topic);
			
			return topic.getParticipants().size();
		}
		throw new Exception("专题不存在或您没有操作权限");
	}
	
	/**
	 * @param opUser
	 * @param topicId
	 * @param users
	 * @param isSynchronous 是否同时在可见人员里面也删这些人
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public int remvoceParticipants(User opUser,String topicId,Set<User> users,boolean isSynchronous) throws Exception {
		Topic topic = topicDao.load(topicId);
		
		if(null!=topic && topic.getAuthor().equals(opUser)) {
			if(null!=topic.getParticipants()) {
				topic.getParticipants().removeAll(users);
				
				topicDao.save(topic);
				
				if(isSynchronous) {
					remvoceVisibleUsers(opUser, topicId, users,false);
				}
				return topic.getParticipants().size();
			}
		}
		
		throw new Exception("专题不存在或您没有操作权限");
	}
	
	@Transactional
	public Topic createTopic(Topic topic){
		
		try {
			//你在新建的时候加的什么人啊
			//topic.getVisibleUsers().addAll(topic.getParticipants());//把参与人员加到可见人员里面
			return topicDao.save(topic);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return topic;
		
	}
	
	public Topic getTopicByid(String id){
		return topicDao.load(id);
	}
	
	@Transactional
	public PageResult find(Map<String,String> params, PageInfo page) {
		PageResult result = new PageResult();
		result.find(page, "select t from Topic t /~visibleUsers: join t.visibleUsers v ~/ /~participants: join t.participants p ~/ where 1=1 /~id: and t.id={id}~/"
				+ "/~title: and t.title like '%[title]%'~/"
				+ "/~type: and t.type.code = {type} ~/"
				+ "/~promiseTime: and t.promiseTime between {promiseTimeBegin} and {promiseTimeEnd}~/"
				+ "/~endTime: and t.promiseTime between {endTimeBegin} and {endTimeEnd}~/"
				+ "/~publish: and t.publish = {publish} ~/"
				+ "/~visibleUsers: and v.id = {visibleUsers} ~/"
				+ "/~participants: and p.id = {participants} ~/"
				+ "/~author_id: and t.author.id = {author_id} ~/", 
				params, topicDao);
		return result;
	}
	
	public void deleteTopic(String topicId){
		Topic topic = topicDao.load(topicId);
		User user = AuthUtil.getCurrentUser();
		if(user.getId().equals(topic.getAuthor().getId())){
			topicDao.delete(topicId);
		}else{
			throw new BusinessException(403, "你不是专题创建者，删除失败。");
		}
	}
	
	@Transactional
	public TopicEdit getTopicEdit(String id, User opUser){
		Topic topic = getTopicByid(id);
		int permissions =0;
		if(topic.getAuthor().getId().equals(opUser.getId())){
			permissions = 2;
		}else if(topic.getParticipants().contains(opUser)){
			permissions = 1;
		}
		return new TopicEdit(topic, permissions);
	}
	
	/**
	 * 只有作者和共建人能修改,修改会记log
	 * 
	 * @param topic
	 * @param opUser
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Topic updateTopic(Topic topic,User opUser) throws Exception{
		
		//效验是否是作者或者是共建人
		Topic topicOld = topicDao.findOne(topic.getId());
		//人员操作就不能放到另外的方法里处理吗？
		//topic.getVisibleUsers().addAll(topic.getParticipants());//把参与人员加到可见人员里面
		
		topic.setAuthor(topicOld.getAuthor());
		topic.setParticipants(topicOld.getParticipants());
		topic.setVisibleUsers(topicOld.getVisibleUsers());
		if(topicOld.getAuthor().getId().equals(opUser.getId())){//作者,什么都能改
			//记录个修改记录
			TopicLog log = saveTopicLog(topic, opUser, topicOld);
			topicLogDao.save(log);
			topicDao.save(topic);
			
		}else if(topicOld.getParticipants().contains(opUser)){//参与者只能改内容
			
			Topic old = new Topic();
			BeanUtils.copyProperties(topicOld, old);
			topicOld.setContent(topic.getContent());
			topicOld.setUpdateTime(new Date());
			TopicLog log = saveTopicLog(topicOld, opUser, old);//造一条只修改了个内容的log

			topicDao.save(topicOld);
			topicLogDao.save(log);
			
		}else{
			throw new Exception("您无权修改该主题!");
		}
		
		return topic;
	}

	private TopicLog saveTopicLog(Topic topic, User opUser, Topic topicOld) {
		Map<String,Object> m = CompareUtils.getModifyContent(topicOld, topic);
		
		TopicLog log = new TopicLog();
		log.setTopicId(topicOld.getId());
		log.setId(topic.getId());
		log.setOpuserId(opUser.getId());
		log.setOpuserName(opUser.getNickName());
		log.setContent(transform(m,topicOld));
		return log;
	}

	private String transform(Map<String, Object> m, Topic topicOld) {
		Map<String,Object> nm = new HashMap();
		if(m.containsKey("title")){
			nm.put("标题", topicOld.getTitle()+"==>"+m.get("title"));
		}
		
		if(m.containsKey("content")){
			nm.put("内容", topicOld.getTitle()+"==>"+m.get("content"));
		}
		
		if(m.containsKey("topic_visible_user")){
			nm.put("可见人员", topicOld.getVisibleUsers()+"==>"+m.get("topic_visible_user"));
		}
		
		if(m.containsKey("remark")){
			nm.put("备注", topicOld.getRemark()+"==>"+m.get("remark"));
		}
		
		if(m.containsKey("participants")){
			nm.put("参与人员", topicOld.getParticipants()+"==>"+m.get("participants"));
		}
		
		if(m.containsKey("location")){
			nm.put("地点", topicOld.getLocation()+"==>"+m.get("location"));
		}
		
		StringBuffer sb = new StringBuffer();
		Iterator i = nm.keySet().iterator();
		while(i.hasNext()){
			String k = (String) i.next();
			String v = (String) nm.get(k);
			sb.append(k).append(":").append(v);
		}
		
		return sb.toString();
	}

	public List<TopicLog> getTopicLogByTopicId(String topicId){
		return topicLogDao.find("from TopicLog where topicId = ? order by optime desc", topicId);
	}
	
	public boolean addTopicReply(TopicReply topicReply){
		try {
			topicReplyDao.save(topicReply);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void deleteTopicReply(String topicReplyId){
		topicReplyDao.delete(topicReplyId);
	}
	
	@Transactional
	public PageResult findTopicReply(Map<String,String> params, PageInfo page) {
		PageResult result = new PageResult();
		result.find(page, "from TopicReply tr where 1=1"
				+ "/~topicId: and tr.topicId = {topicId} ~/",
				params, topicDao);
		return result;
	}
	
	@Transactional
	public void addTopicWork(TopicWork topicWork){
		List<TopicWork> list = topicWorkDao.find("from TopicWork where topicId = ? and author.id = ?", topicWork.getTopicId(),topicWork.getAuthor().getId());
		if(null==list || list.isEmpty()){
			//新增
			topicWorkDao.save(topicWork);
		}else{
			//修改
			TopicWork old = list.get(0);
			old.setUrl(topicWork.getUrl());
			old.setUpdateTime(new Date());
			topicWorkDao.save(old);
		}
	}
	
	public List<TopicWork> getTopicWorkByTopicId(String topicId){
		return topicWorkDao.find("from TopicWork where topicId = ? ", topicId);
	}

	@Transactional
	public List<User> getVisibleUsers(String topicId) {
		// TODO Auto-generated method stub
		Set<User> users = getTopicByid(topicId).getVisibleUsers();
		users.size();
		return new ArrayList<User>(users);
	}
	
	@Transactional
	public List<User> getParticipants(String topicId) {
		// TODO Auto-generated method stub
		Set<User> participants = getTopicByid(topicId).getParticipants();
		participants.size();
		return new ArrayList<>(participants);
	}

	public Long countByType(String code) {
		return topicDao.count("from Topic where publish = 1 and type.code=?", code);
	}
}
