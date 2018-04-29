package cn.qlt.domain.dto;

import java.util.Date;

import cn.qlt.domain.Dict;
import cn.qlt.domain.User;

public class UserDto {
private String id;
	
	private String loginname;
	
	private String password;
	
	private String nickName;
	
	private Date lastLoginTime;
	
	private String specialty;
	
	private String grade;
	
	private String classes;
	
	private String year;
	
	private int status = 1;

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

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public User getUser(){
		return new User(id, loginname, password, nickName, new Dict(specialty), new Dict(grade), new Dict(classes), new Dict(year), status);
	}
}
