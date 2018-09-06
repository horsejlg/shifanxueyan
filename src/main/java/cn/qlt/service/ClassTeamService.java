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
		if(null==student.getClassTeam()) {
			ClassTeam t = new ClassTeam();
			t.setYear(student.getUser().getYear());
			t.setGrade(student.getUser().getGrade());
			t.setSpecialty(student.getUser().getSpecialty());
			t.setClasses(student.getUser().getClasses());
			
			List<ClassTeam> classTeams = classTeamDao.find("from ClassTeam where year=? and grade=? and specialty=? and classes=?", t.getYear(),t.getGrade(),t.getSpecialty(),t.getClasses());
			if(null==classTeams || classTeams.isEmpty()) {//如果对应的班级没有
				//就新增
				classTeamDao.save(t);
				student.setClassTeam(t);
				studentDao.save(student);
			}
		}else {
			classTeamDao.save(student.getClassTeam());
			studentDao.save(student);
		}
		
	} 

}
