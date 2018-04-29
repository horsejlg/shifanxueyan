package cn.qlt.domain.business;

import javax.persistence.Id;

import cn.qlt.utils.DomainObject;
import cn.qlt.utils.ManagedIdentityDomainObject;

/**
 * 
 * 专业
 * @author zp
 *
 */
public class Specialty extends DomainObject<Specialty>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9021021800756158941L;

	private String name;
	
	@Id
	private String code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	

}
