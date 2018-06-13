package cn.qlt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qlt.dao.AssistantDao;
import cn.qlt.domain.Assistant;

@Service
public class AssistantService {
	
	@Autowired
	private AssistantDao assistantDao;
	
	public Assistant loadAssistantById(String id){
		
		return assistantDao.load(id);
	}

}
