package cn.qlt.domain.business;

import java.util.List;

import cn.qlt.domain.User;
import cn.qlt.utils.ManagedIdentityDomainObject;

/**
 * 
 * 班级
 * @author zp
 *
 */
public class StuClass extends ManagedIdentityDomainObject<StuClass>{
	
	/**
	 * 年级
	 */
	private StuGrade stuGrade;
	
	private List<User> users;

}
