package cn.qlt.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.qlt.dao.MessageDao;
import cn.qlt.domain.Message;
import cn.qlt.domain.User;
import cn.qlt.utils.web.AuthUtil;

@Service
public class MessageService {

	@Autowired
	private MessageDao messageDao;
	
	public List<Map<String, Object>> findMessageByUser(User to){
		return messageDao.findByTosAndState(to.getId(), 1);
	}
	
	public void saveMessage(Message message){
		messageDao.save(message);
	}
	
	public void removeMessage(String id){
		messageDao.delete(id);
	}

	@Transactional
	public boolean updateState(String id, int state) {
		Message message = messageDao.load(id);
		if(message.getTos().equals(AuthUtil.getCurrentUser())){
			message.setState(state);
			messageDao.save(message);
			return true;
		}else{
			return false;
		}
	}
}
