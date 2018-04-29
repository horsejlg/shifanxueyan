package cn.qlt.domain;

import java.util.Date;

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
 * 获奖
 *
 */
@Entity
@Table(name="awards")
public class Awards extends ManagedIdentityDomainObject<Evaluation>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7442925514366805113L;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	@Column(name="awards", length=50)
	private String awards;
	
	@Column(name="timed")
	private Date timed;
	
	@Column(name="level", length=10)
	private String level;


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

	public Date getTimed() {
		return timed;
	}

	public void setTimed(Date timed) {
		this.timed = timed;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	
}
