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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@JsonIgnore
	@ManyToMany(mappedBy="classess", fetch = FetchType.LAZY)
	private List<Assistant> assistants;
	
	@JsonIgnore
	@OneToMany(mappedBy="classTeam", fetch = FetchType.LAZY, cascade=CascadeType.DETACH)
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
		return "ClassTeam [id= "+id+"specialty=" + specialty + ", grade=" + grade + ", classes=" + classes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((assistants == null) ? 0 : assistants.hashCode());
		result = prime * result + ((classes == null) ? 0 : classes.hashCode());
		result = prime * result + ((grade == null) ? 0 : grade.hashCode());
		result = prime * result + ((specialty == null) ? 0 : specialty.hashCode());
		result = prime * result + ((students == null) ? 0 : students.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		ClassTeam other = (ClassTeam) obj;
		
		if (classes == null) {
			if (other.classes != null)
				return false;
		} else if (!classes.equals(other.classes))
			return false;
		if (grade == null) {
			if (other.grade != null)
				return false;
		} else if (!grade.equals(other.grade))
			return false;
		if (specialty == null) {
			if (other.specialty != null)
				return false;
		} else if (!specialty.equals(other.specialty))
			return false;
		
		return true;
	}

	
}
