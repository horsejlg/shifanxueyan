package cn.qlt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.qlt.dao.AssistantDao;
import cn.qlt.dao.AwardsDao;
import cn.qlt.dao.UserDao;
import cn.qlt.domain.Assistant;
import cn.qlt.domain.Awards;
import cn.qlt.domain.ClassTeam;
import cn.qlt.domain.Dict;
import cn.qlt.domain.Student;
import cn.qlt.domain.User;
import cn.qlt.utils.SQLUtils.PageInfo;
import cn.qlt.utils.SQLUtils.PageResult;

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
	
	@Autowired
	private ClassTeamService classteamService;
	
	@Autowired
	private AwardsDao awardsDao;
	
	@Autowired
	private StudentService studentService;
	
	@Transactional
	public Assistant loadAssistantById(String id){
		Assistant a = assistantDao.findOne(id);
		if(null==a){
			a = new Assistant();
			a.setId(id);
			a.setUser(userDao.load(id));
		}
		
		if(null!=a) {
			List<Awards> findByUser2 = awardsDao.findByUser(a.getUser());
			a.setAwards(findByUser2);
		}
		
		return a;
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

	@Transactional
	public void saveGrade(String id,Dict grades) {
		Assistant ass = assistantDao.load(id);
		if(null == ass) {
			User u = userDao.load(id);
			ass = new Assistant();
			ass.setUser(u);
			ass.setId(id);
			assistantDao.save(ass);
		}
		ass.getGrades().add(grades);
		assistantDao.save(ass);
	}

	@Transactional
	public void deleteGrade(String id,Dict grades) {
		Assistant ass = assistantDao.load(id);
		ass.getGrades().remove(grades);
		assistantDao.save(ass);
	}

	@Transactional
	public void saveClassTeam(String id, ClassTeam classTeam) {
		Assistant ass = assistantDao.load(id);
		if(null == ass) {
			User u = userDao.load(id);
			ass = new Assistant();
			ass.setUser(u);
			ass.setId(id);
			assistantDao.save(ass);
		}
		
		classTeam = classteamService.checkAndSave(classTeam);
		
		ass.getClassess().add(classTeam);
		assistantDao.save(ass);
	}

	@Transactional
	public void deleteClassTeam(String id, ClassTeam classTeam) {
		Assistant ass = assistantDao.load(id);
		ass.getClassess().remove(classTeam);
		assistantDao.save(ass);
	}
	
	public void saveAwards(Awards awards) {
		awardsDao.save(awards);
	}
	
	public void deleteAwards(String id) {
		awardsDao.delete(id);
	}

	@Transactional
	public Set<Assistant> findAssistantByStdId(String userId) {
		List<Assistant> assList = new ArrayList<Assistant>();
		
		Student stu = studentService.loadFullStudent(userId);
		assList.addAll(stu.getClassTeam().getAssistants());
		
		List p = assistantDao.find("select ass from Assistant ass join ass.grades grad where grad.code = ?", stu.getClassTeam().getGrade().getCode());
		
		assList.addAll(p);
		
		Set set = new HashSet(assList);
		return set;
	}


}
