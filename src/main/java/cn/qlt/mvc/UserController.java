package cn.qlt.mvc;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.qlt.domain.Role;
import cn.qlt.domain.User;
import cn.qlt.service.UserService;
import cn.qlt.utils.SQLUtils;
import cn.qlt.utils.SQLUtils.PageResult;
import cn.qlt.utils.web.Auth;
import cn.qlt.utils.web.AuthUtil;
import cn.qlt.utils.web.RequestUtil;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value="/logon")
	public boolean login(String loginname, String password, HttpServletRequest request) throws Exception{
			User user = userService.login(loginname, password);
			AuthUtil.login(request,user);
		return true;
	}
	@Auth
	@PostMapping(value="/console/users")
	public PageResult findUser(HttpServletRequest request){
		Map<String, String> params = RequestUtil.getParams(request);
		return userService.find(params, SQLUtils.getPageInfo(params));
	}
	@Auth(role="master")
	@PostMapping(value="/console/user")
	public boolean updateUser(@RequestBody User user){
		return userService.updateUser(user);
	}
	
	@Auth
	@PostMapping(value="/me/user")
	public boolean saveMe(@RequestBody User user,HttpSession session){
		if(userService.updateUser(user)){
			session.setAttribute("user", user);
			return true;
		}else{
			return false;
		}
	}
	@Auth(role="master")
	@DeleteMapping(value="/console/user/{id}")
	public boolean deleteUser(@PathVariable String id){
		return userService.deleteUser(id);
	}
	
	@GetMapping(value="/console/roles")
	public List<Role> getAllRole(){
		return userService.findAllRole();
	}
	@Auth(role="master")
	@PostMapping(value="/console/roles")
	public boolean updateRoles(String id, String[] roles){
		return userService.updateRoles(id, roles);
	}
	
	@PostMapping(value="/public/canUse")
	public boolean canUse(String loginname){
		return !userService.checkUser(loginname);
	}
	@Auth(role="master")
	@PostMapping(value="/console/checkUser")
	public boolean checkUser(String id, String loginname){
		return userService.checkUser(id, loginname);
	}
	
	@Auth
	@GetMapping(value="/logOut")
	public boolean logout(HttpSession session){
		session.removeAttribute("user");
		AuthUtil.leave();
		return true;
	}
	
	@Auth
	@PostMapping("/password")
	public boolean mePassword(String oldPwd, String newPwd){
		return userService.updatePassword(AuthUtil.getCurrentUser(), oldPwd, newPwd);
	}
	
	@Auth(role=AuthUtil.SYSTEM)
	@PostMapping("/console/password")
	public boolean passwordUpdate(String id, String pwd){
		return userService.updatePasswordByMaster(id, pwd);
	}
	
	/*
	@RequestMapping("/console/user/{loginname}")
	public User load(@PathVariable("loginname")String loginname){
		return userService.load(loginname);
	}
	
	@RequestMapping(value="/console/user", method={RequestMethod.POST})
	public boolean save(User user) throws Exception{
		User current = AuthUtil.getCurrentUser();
		if(current.getLoginname().equals(user.getLoginname()) || current.getRole().equals(Role.admin)){

			userService.save(user);
			return true;
		}else{
			return false;
		}
	}*/
}
