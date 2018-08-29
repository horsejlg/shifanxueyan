package cn.qlt.service;

import java.util.List;

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
	public Student getStudentById(String id){
		return studentDao.load(id);
	}
	
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
	
	
}
