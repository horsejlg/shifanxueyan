package cn.qlt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.qlt.dao.AssistantDao;
import cn.qlt.dao.UserDao;
import cn.qlt.domain.Assistant;
import cn.qlt.domain.User;

/**
 * @author zp
 * 辅导员
 */
@Service
public class AssistantService {
	
	@Autowired
	private AssistantDao assistantDao;
	
	@Autowired
	private UserDao userDao;
	
	public Assistant loadAssistantById(String id){
		
		return assistantDao.load(id);
	}
	
	public boolean updateAssistant(Assistant assistant){
		try {
			assistantDao.save(assistant);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Transactional
	public void saveAssistant(Assistant assistant) {
		User user = assistant.getUser();
		User old = userDao.load(assistant.getId());
		user.setPassword(old.getPassword());
		user.setLoginname(old.getLoginname());
		user.setLastLoginTime(old.getLastLoginTime());
		userDao.save(user);
		assistantDao.save(assistant);
	}

}
