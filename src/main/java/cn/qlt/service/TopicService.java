package cn.qlt.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.qlt.dao.TopicDao;
import cn.qlt.dao.TopicLogDao;
import cn.qlt.domain.Topic;
import cn.qlt.domain.TopicLog;
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
	
	public boolean createTopic(Topic topic){
		
		try {
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
		result.find(page, "from Topic t where 1=1 /~id: and t.id={id}~/"
				+ "/~title: and t.title like '%[title]%'~/"
				+ "/~promiseTime: and t.promiseTime between {promiseTimeBegin} and {promiseTimeEnd}~/"
				+ "/~endTime: and t.promiseTime between {endTimeBegin} and {endTimeEnd}~/"
				+ "/~publish: and t.publish = {publish} ~/"
				+ "/~author_id: and t.author.id = {author_id} ~/", 
				params, topicDao);
		return result;
	}
	
	/**
	 * 只有作者和共建人能修改,修改会记log
	 * 
	 * @param topic
	 * @param opUser
	 * @return
	 * @throws Exception
	 */
	public Topic updateTopic(Topic topic,User opUser) throws Exception{
		
		//效验是否是作者或者是共建人
		Topic topicOld = topicDao.load(topic.getId());
		
		if(topicOld.getAuthor().getId().equals(opUser.getId())||topicOld.getParticipants().contains(opUser)){
			topicDao.save(topic);
			//记录个修改记录
			TopicLog log = saveTopicLog(topic, opUser, topicOld);
			topicLogDao.save(log);
		}else{
			throw new Exception("您无权修改该主题!");
		}
		
		return topic;
	}

	private TopicLog saveTopicLog(Topic topic, User opUser, Topic topicOld) {
		Map<String,Object> m = CompareUtils.getModifyContent(topicOld, opUser);
		
		TopicLog log = new TopicLog();
		log.setId(topic.getId());
		log.setOpuserId(opUser.getId());
		log.setOpuserName(opUser.getNickName());
		log.setContent(m.toString());//TODO 这里要翻译下字段名
		return log;
	}
}
