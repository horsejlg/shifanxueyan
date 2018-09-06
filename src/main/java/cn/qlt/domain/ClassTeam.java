package cn.qlt.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cn.qlt.utils.ManagedIdentityDomainObject;

/**
 * @author zhangpeng
 * 班级
 */
@Entity
@Table(name="classTeam")
public class ClassTeam extends ManagedIdentityDomainObject<ClassTeam>{

	private static final long serialVersionUID = -8202799081467279898L;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="specialty",nullable=true)
	private Dict specialty;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="grade",nullable=true)
	private Dict grade;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="classes",nullable=true)
	private Dict classes;
	
	@ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="years",nullable=true)
	private Dict year;
	
	@ManyToMany(mappedBy="classess")
	private List<Assistant> assistants;
	
	@OneToMany(mappedBy="classTeam", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Student> students = new ArrayList<Student>();

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public List<Assistant> getAssistants() {
		return assistants;
	}

	public void setAssistants(List<Assistant> assistants) {
		this.assistants = assistants;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "ClassTeam [specialty=" + specialty + ", grade=" + grade + ", classes=" + classes + ", year=" + year
				+ "]";
	}
	
}
