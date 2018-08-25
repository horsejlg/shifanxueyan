package cn.qlt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qlt.dao.AssistantDao;
import cn.qlt.domain.Assistant;

/**
 * @author zp
 * 辅导员
 */
@Service
public class AssistantService {
	
	@Autowired
	private AssistantDao assistantDao;
	
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

}
