package cn.qlt.service;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.NoResultException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.qlt.dao.RoleDao;
import cn.qlt.dao.StudentDao;
import cn.qlt.dao.UserDao;
import cn.qlt.domain.Role;
import cn.qlt.domain.Student;
import cn.qlt.domain.User;
import cn.qlt.utils.SQLUtils;
import cn.qlt.utils.SQLUtils.PageInfo;
import cn.qlt.utils.SQLUtils.PageResult;
import cn.qlt.utils.SQLUtils.SqlItem;
import cn.qlt.utils.web.BusinessException;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private StudentDao stuDao;
	
	@Autowired
	private ClassTeamService classTeamService;
	
	@Autowired
	private StudentService studentService;
	
	@Transactional
	public User login(String loginname, String password) throws Exception{
		try{
		User loadUser = userDao.findSingle("loginname", loginname);
		if(loadUser==null)
			throw new Exception("用户不存在");
		if(loadUser.checkPassword(password)){
			loadUser.setLastLoginTime(new Date());
			userDao.save(loadUser);
			return loadUser;
		}else{
			throw new Exception("密码错误");
		}
		}catch (NoResultException e) {
			throw new Exception("用户不存在");
		}
	}
	
	public User loadUser(String id){
		return userDao.findOne(id);
	}
	
	@PostConstruct
	@Transactional
	public void init(){
		Role system;
		if(roleDao.count()==0){
			Role role = new Role();
			role.setCode("class");
			role.setLabel("班级管理员");
			roleDao.save(role);
			role = new Role();
			role.setLabel("教师管理员");
			role.setCode("teacher");
			roleDao.save(role);
			role = new Role();
			role.setCode("master");
			role.setLabel("系统管理员");
			roleDao.save(role);
			
			role = new Role();
			role.setCode("assistant");
			role.setLabel("辅导员");
			roleDao.save(role);
			
			system = role;
		}else if(roleDao.count()==3){
			Role role = new Role();
			role.setCode("assistant");
			role.setLabel("辅导员");
			roleDao.save(role);
		}
		
			system = roleDao.findSingle("code", "master");
	
		Role stuRole = null;
		try{
		stuRole= roleDao.findSingle("code", "student");
		}catch (Exception e) {}
		if(stuRole==null){
			Role role = new Role();
			role.setCode("student");
			role.setLabel("学生");
			roleDao.save(role);
		}
			
		//如果一个都没,就自动建一个
		if(!this.checkUser("administrator")){
			User user = new User();
			user.setLoginname("administrator");
			user.setNickName("系统管理员");
			user.setPassword("123456");
			user.getRoles().add(system);
			user.encodedPassword();
			userDao.save(user);
			System.out.println("##############################");
			System.out.println(userDao.count("from User", null));
			System.out.println("##############################");
		}
		
		//批量处理下学生班级
		/*Iterator<User> st = userDao.findAll().iterator();
		while(st.hasNext()) {
			Student s = studentService.loadFullStudent(st.next().getId());
			if(null!=s && null==s.getClassTeam()) {
				classTeamService.saveClassTeam(s);
			}
		}*/
	}
	
	public List<Role> findAllRole(){
		return roleDao.find("from Role",null);
	}
	
	
	@Transactional
	public PageResult find(Map<String,String> params, PageInfo page) {
		PageResult result = new PageResult();
		result.find(page, "from User user where 1=1 /~id: and user.id={id}~//~loginname: and user.loginname ={loginname}~//~nickName: and user.nickName like '%[nickName]%'~//~specialty: and specialty.code={specialty} ~//~grade: and grade.code={grade} ~//~classes: and classes.code={classes} ~//~classes: and classes.code={classes} ~/", params, userDao);
		return result;
	}
	
	public Long count(Map<String,String> params){
		SqlItem sqlItem = SQLUtils.markSql("select count(0) from user where 1=1 /~id: and id={id}//~loginname: and loginname like '%[loginname]%'~//~nickName: and nickName like '%{nickName}%'~/", params);
		return userDao.count(sqlItem.getSql(), sqlItem.getValues().toArray());
	}

	public boolean updateUser(User user) {
		if(StringUtils.isEmpty(user.getId())){
			user.setPassword("123456");
			user.encodedPassword();
		}else{
			User old = userDao.load(user.getId());
			BeanUtils.copyProperties(user, old, "password", "lastLoginTime","roles");
			user = old;
		}
		if(StringUtils.isEmpty(user.getPolitics().getCode())){
			user.setPolitics(null);
		}
		if(StringUtils.isEmpty(user.getJob().getCode())){
			user.setJob(null);
		}
		userDao.save(user);
		
		return true;
	}
	
	public boolean checkUser(String loginname){
		return userDao.count("from User where loginname = ?", loginname)!=0;
	}

	public boolean deleteUser(String id) {
		userDao.delete(id);
		return true;
	}

	public boolean checkUser(String id, String loginname) {
		try{
		User user = userDao.findSingle("loginname", loginname);
	
		if(user.getId().equals(id))
			return true;
		return false;
		}catch (EmptyResultDataAccessException  e) {
			return true;// TODO: handle exception
		}
	}

	public boolean updateRoles(String id, String[] roles) {
		User user = userDao.load(id);
		Arrays.sort(roles);
		LinkedHashSet<Role> roleSet = new LinkedHashSet<Role>();
		for(String role:roles){
			Role r = new Role();
			r.setCode(role);
			roleSet.add(r);
		}
		user.setRoles(roleSet);
		userDao.save(user);
		return true;
	}

	public boolean updatePassword(User user, String oldPwd, String newPwd) {
		if(StringUtils.isEmpty(oldPwd)){
			throw new BusinessException(500, "原密码不能为空");
		}
		if(StringUtils.isEmpty(newPwd)){
			throw new BusinessException(500, "新密码不能为空");
		}
		if(oldPwd.equals(newPwd)){
			throw new BusinessException(500, "新旧密码不能相同");
		}
		if(user.checkPassword(oldPwd)){
			user.setPassword(newPwd);
			user.encodedPassword();
			userDao.save(user);
			return true;
		}else{
			throw new BusinessException(500, "输入的原密码不正确");
		}
	}
	
	public boolean updatePasswordByMaster(String id, String password){
		if(StringUtils.isEmpty(password)){
			throw new BusinessException(500, "密码不能为空");
		}else{
			User user = userDao.load(id);
			user.setPassword(password);
			user.encodedPassword();
			userDao.save(user);
			return true;
		}
	}

	
}
