package cn.qlt.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.qlt.utils.ManagedIdentityDomainObject;

/**
 * @author 江立国
 * 社会关系
 */
@Entity
@Table(name="sociogram")
public class Sociogram extends ManagedIdentityDomainObject<Sociogram>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4450379999330108698L;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	/**
	 * 姓名
	 */
	@Column(name="name",length=20,nullable=false)
	private String name;
	
	/**
	 * 关系
	 */
	@Column(name="relation",length=20,nullable=false)
	private String relation;
	
	/**
	 * 工作单位
	 */
	@Column(name="unit",length=50,nullable=false)
	private String unit;
	
	/**
	 * 电话
	 */
	@Column(name="phone", length=13)
	private String phone;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	

}
