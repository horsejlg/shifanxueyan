package cn.qlt.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cn.qlt.utils.DomainObject;
import cn.qlt.utils.ObjectId;

@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED) 
@JsonIgnoreProperties({"password","attributes"})
public class User extends DomainObject<User>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5102685109070282774L;

	@Id
	@Column(name="id", length=24, nullable=false, unique=true)
	private String id;
	
	@Column(name="login_name", length=20,unique=true, nullable=false)
	private String loginname;
	
	@Column(name="pass_word", length=32, nullable=false)
	private String password;
	
	@Column(name="nick_name", length=60)
	private String nickName;
	
	@Column(name="last_login_time")
	private Date lastLoginTime;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="specialty",nullable=true)
	@NotFound(action=NotFoundAction.IGNORE)
	private Dict specialty;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="grade",nullable=true)
	@NotFound(action=NotFoundAction.IGNORE)
	private Dict grade;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="classes",nullable=true)
	@NotFound(action=NotFoundAction.IGNORE)
	private Dict classes;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="years",nullable=true)
	private Dict year;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="politics",nullable=true)
	private Dict politics;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="job",nullable=true)
	private Dict job;
	
	@ManyToMany(cascade = CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinTable(
            name="user_role",joinColumns={@JoinColumn(name="user_id")},inverseJoinColumns={@JoinColumn(name="role_code")}
    )
	private Set<Role> roles = new HashSet<Role>();
	
	@Column
	private int status = 1;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private List<UserAttribute> attributes = new ArrayList<UserAttribute>();
	
	public User() {
		super();
	}

	public User(String id) {
		super();
		this.id = id;
	}

	public User(String loginname, String password, String nickName,
			Date lastLoginTime, Set<Role> roles) {
		super();
		this.loginname = loginname;
		this.password = password;
		this.nickName = nickName;
		this.lastLoginTime = lastLoginTime;
		this.roles = roles!=null?roles:this.roles;
	}
	
	
	
	public User(String id, String loginname, String password, String nickName, Dict specialty,
			Dict grade, Dict classes, Dict year, int status) {
		super();
		this.id = id;
		this.loginname = loginname;
		this.password = password;
		this.nickName = nickName;
		this.specialty = specialty;
		this.grade = grade;
		this.classes = classes;
		this.year = year;
		this.status = status;
	}
	
	public User(String id, String nickName) {
		super();
		this.id = id;
		this.nickName = nickName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	public User(String id, String loginname, String nickName, String specialtyCode, String specialtyLabel, String gradeCode, String gradeLabel,
			String classesCode, String classeslabel, String yearCode, String yearlabel, String politicsCode, String politicsLabel, String jobCode, String jobLabel, int status) {
		super();
		this.id = id;
		this.loginname = loginname;
		this.nickName = nickName;
		this.specialty = new Dict(specialtyCode,specialtyLabel);
		this.grade = new Dict(gradeCode, gradeLabel );
		this.classes = new Dict(classesCode, classeslabel);
		this.year = new Dict(yearCode, yearlabel);
		this.politics = new Dict(politicsCode, politicsLabel);
		this.job = new Dict(jobCode, jobLabel );
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<UserAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<UserAttribute> attributes) {
		this.attributes = attributes;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void encodedPassword(){
		try {
			if(StringUtils.isEmpty(id))
				id = ObjectId.get().toString();
			//password = XOR.encoder(password, id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean checkPassword(String password){
		try {
			if(//XOR.encoder(password, id)
					password.equals(this.password)){
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Dict getSpecialty() {
		return specialty;
	}

	public void setSpecialty(Dict specialty) {
		this.specialty = specialty;
	}

	public Dict getGrade() {
		return grade;
	}

	public void setGrade(Dict grade) {
		this.grade = grade;
	}

	public Dict getClasses() {
		return classes;
	}

	public void setClasses(Dict classes) {
		this.classes = classes;
	}

	public Dict getYear() {
		return year;
	}

	public void setYear(Dict year) {
		this.year = year;
	}

	public Dict getPolitics() {
		return politics;
	}

	public void setPolitics(Dict politics) {
		this.politics = politics;
	}

	public Dict getJob() {
		return job;
	}

	public void setJob(Dict job) {
		this.job = job;
	}
	
	@Override
	public String toString() {
		return "User [loginname=" + loginname + ", nickName=" + nickName + "]";
	}
	
	

	
}
