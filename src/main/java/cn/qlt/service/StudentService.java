package cn.qlt.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.qlt.dao.AwardsDao;
import cn.qlt.dao.SociogramDao;
import cn.qlt.dao.StudentDao;
import cn.qlt.dao.UserDao;
import cn.qlt.domain.Awards;
import cn.qlt.domain.Sociogram;
import cn.qlt.domain.Student;
import cn.qlt.domain.User;
import cn.qlt.utils.SQLUtils.PageInfo;
import cn.qlt.utils.SQLUtils.PageResult;

@Service
public class StudentService {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private SociogramDao sociogramDao;
	
	@Autowired
	private AwardsDao awardsDao;
	
	@Transactional
	public Student loadFullStudent(String id){
		Student student = studentDao.load(id);
		if(student!=null){
			List<Sociogram> findByUser = sociogramDao.findByUser(student.getUser());
			student.setSociograms(findByUser);
			List<Awards> findByUser2 = awardsDao.findByUser(student.getUser());
			student.setAwards(findByUser2);
		}else{
			student = new Student();
			student.setId(id);
			student.setUser(userDao.load(id));
		}
		return student;
	}
	
	public PageResult find(Map<String,String> params, PageInfo page) {
		PageResult result = new PageResult();
		result.find(page, "select user from Student where 1=1 /~userId: and user.id = {userId}~//~name: and user.nickName like '%[name]%'~//~grade: and user.grade.code = {grade} ~//~specialty: and user.specialty.code = {specialty} ~//~classes: and user.classes.code = {classes} ~//~year: and user.code = {year} ~/", params, studentDao);
		return result;
	}
	
	@Transactional
	public boolean saveStudent(Student student){
		User user = student.getUser();
		User old = userDao.load(student.getId());
		user.setPassword(old.getPassword());
		user.setLoginname(old.getLoginname());
		user.setLastLoginTime(old.getLastLoginTime());
		userDao.save(user);
		studentDao.save(student);
		return true;
	}

	public void saveSociogram(Sociogram sociogram) {
		sociogramDao.save(sociogram);
	}

	public void saveAwards(Awards awards) {
		awardsDao.save(awards);
	}

	public void deleteSociogram(String id) {
		sociogramDao.delete(id);
	}

	public void deleteAwards(String id) {
		awardsDao.delete(id);
	}
	
}
