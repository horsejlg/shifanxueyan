package cn.qlt.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import cn.qlt.utils.DomainObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="role")
@JsonIgnoreProperties(value={"users"})
public class Role extends DomainObject<Role>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2467858954598700905L;

	@Id
	@Column(name="code", length=20, nullable=false)
	private String code;
	
	@Column(name="label", length=20, nullable=false)
	private String label;
	
	@ManyToMany(mappedBy="roles", cascade=CascadeType.DETACH)
	private List<User> users = new ArrayList<User>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	
}
