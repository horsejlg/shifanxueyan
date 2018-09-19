package test.cn.qlt;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.qlt.dao.RoleDao;
import cn.qlt.dao.UserDao;
import cn.qlt.domain.Role;
import cn.qlt.domain.User;
import cn.qlt.domain.UserAttribute;
import cn.qlt.service.UserService;
import cn.qlt.utils.ObjectId;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/conf-spring/spring-db.xml" })
@Transactional
public class UserTest {

	
	@PersistenceContext
	EntityManager em;

	@Autowired
	UserDao userdao;
	
	@Autowired
	RoleDao roleDao;

	Role[] roles = new Role[3];
	
	@Autowired
	UserService us;

	//@Before
	public void insertRole() {
		List<Role> list = em.createQuery("from Role").getResultList();
		if (list.isEmpty()) {
			Role role = new Role();
			role.setCode("class");
			role.setLabel("班级管理员");
			roleDao.save(role);
			roles[0] = role;
			role = new Role();
			role.setLabel("教师管理员");
			role.setCode("teacher");
			roleDao.save(role);
			roles[1] = role;
			role = new Role();
			role.setCode("master");
			role.setLabel("系统管理员");
			roleDao.save(role);
			roles[2] = role;
		}else{
			int i =0;
			for(Role r:list){
				roles[i++] = r;
			}
		}
		List<User> list2 = userdao.find("from User");
		for(User u:list2)
			em.remove(u);
		em.flush();
	}

	//@Test
	public void insertUser() throws Exception {
/*		for (int i = 0; i < 100; i++) {
			User user = new User();
			user.setLoginname("test" + i);
			user.setPassword("test");
			if (i % 6 == 0) {
				user.getRoles().add(roles[1]);
			} else {
				user.getRoles().add(roles[0]);
			}
			if (i % 15 == 0)
				user.getRoles().add(roles[2]);
			UserAttribute att = new UserAttribute();
			att.setId(ObjectId.get().toString());
			att.setLabel(i%7+"");
			att.setUser(user);
			user.getAttributes().add(att);
			att = new UserAttribute();
			att.setId(ObjectId.get().toString());
			att.setLabel(i%11+"");
			att.setUser(user);
			user.getAttributes().add(att);
			att.setId(ObjectId.get().toString());
			att.setLabel(i%13+"");
			att.setUser(user);
			user.getAttributes().add(att);
			userdao.insert(user);
		}*/
		//Query query = em.createQuery("select u from User u join u.roles as r  where r.code='teacher' order by u.id", User.class);
		List<User> list = userdao.find("from User");
		System.out.println("++++++++++++++++");
		ObjectMapper mapper = new ObjectMapper();
		JsonGenerator jsonGenerator = mapper.getJsonFactory().createGenerator(System.out);
		for (User u : list) {
			jsonGenerator.writeObject(u);
			System.out.println();
		}
		System.out.println("++++++++++++++++");

	}
	
	//@Test
	public void testCount(){
		/*System.out.println(roleDao.count("from Role", null));
		User loadUser = userdao.findSingle("loginname", "administrator");*/
//		User u = us.loadUser("167da3b154c7a5a102e6aa58");
//		System.out.println(u.checkPassword("123456"));
//		us.updatePasswordByMaster("167da3b154c7a5a102e6aa58", "123456");
//		System.out.println(u.checkPassword("123456"));
	}


}
