package cn.qlt.domain.business;

import java.util.List;

import cn.qlt.utils.ManagedIdentityDomainObject;

/**
 * 
 * 年级
 * @author zp
 *
 */
public class StuGrade extends ManagedIdentityDomainObject<StuGrade>{
	
	/**
	 * 专业
	 */
	private Specialty specialty;
	
	private String name;
	
	private List<StuClass> stuClasses;

}
