package cn.qlt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.qlt.dao.ClassTeamDao;
import cn.qlt.dao.StudentDao;
import cn.qlt.dao.UserDao;
import cn.qlt.domain.ClassTeam;
import cn.qlt.domain.Student;
import cn.qlt.domain.User;

@Service
public class ClassTeamService {
	
	@Autowired
	private ClassTeamDao classTeamDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Transactional
	public void saveClassTeam(Student student) {
		ClassTeam t = new ClassTeam();
		if(null==student.getClassTeam()) {
			t.setGrade(student.getUser().getGrade());
			t.setSpecialty(student.getUser().getSpecialty());
			t.setClasses(student.getUser().getClasses());
			
			t = checkAndSave(t);
			student.setClassTeam(t);
			studentDao.save(student);
		}else {
			t = checkAndSave(t);
			student.setClassTeam(t);
			studentDao.save(student);
		}
		
	}

	private ClassTeam checkAndSave(ClassTeam t) {
		List<ClassTeam> classTeams = classTeamDao.find("from ClassTeam where grade=? and specialty=? and classes=?",t.getGrade(),t.getSpecialty(),t.getClasses());
		if(null==classTeams || classTeams.isEmpty()) {//如果对应的班级没有
			t = classTeamDao.save(t);//就新增
		}else{
			t = classTeams.get(0);
		}
		
		return t;
	} 

}
