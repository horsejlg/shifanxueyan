package cn.qlt.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cn.qlt.utils.ManagedIdentityDomainObject;

@Entity
@Table(name="user_att")
@JsonIgnoreProperties(value={"user"})
public class UserAttribute extends ManagedIdentityDomainObject<UserAttribute> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6974706779766720312L;
	
	@Column(name="label")
	private String label;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",nullable=false)
	private User user;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}
