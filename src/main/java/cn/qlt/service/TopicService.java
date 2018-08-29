package cn.qlt.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.qlt.dao.TopicDao;
import cn.qlt.dao.TopicLogDao;
import cn.qlt.dao.TopicReplyDao;
import cn.qlt.domain.Topic;
import cn.qlt.domain.TopicLog;
import cn.qlt.domain.TopicReply;
import cn.qlt.domain.User;
import cn.qlt.utils.CompareUtils;
import cn.qlt.utils.SQLUtils.PageInfo;
import cn.qlt.utils.SQLUtils.PageResult;

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
	
	public boolean createTopic(Topic topic){
		
		try {
			topic.getVisibleUsers().addAll(topic.getParticipants());//把参与人员加到可见人员里面
			topicDao.save(topic);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public Topic getTopicByid(String id){
		return topicDao.load(id);
	}
	
	@Transactional
	public PageResult find(Map<String,String> params, PageInfo page) {
		PageResult result = new PageResult();
		result.find(page, "select t from Topic t /~visibleUsers: join t.visibleUsers v ~/ /~participants: join t.participants p ~/ where 1=1 /~id: and t.id={id}~/"
				+ "/~title: and t.title like '%[title]%'~/"
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
		topicDao.delete(topicId);
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
		topic.getVisibleUsers().addAll(topic.getParticipants());//把参与人员加到可见人员里面
		
		if(topicOld.getAuthor().getId().equals(opUser.getId())){//作者,什么都能改
			//记录个修改记录
			TopicLog log = saveTopicLog(topic, opUser, topicOld);
			topicLogDao.save(log);
			
			topicDao.save(topic);
			
		}else if(topicOld.getParticipants().contains(opUser)){//参与者只能改内容
			
			Topic old = new Topic();
			BeanUtils.copyProperties(topicOld, old);
			topicOld.setContent(topic.getContent());
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
}
