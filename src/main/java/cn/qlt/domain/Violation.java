package cn.qlt.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.qlt.utils.DomainObject;

@Entity
@Table(name="violation")
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.INTEGER) 
@DiscriminatorValue("0")
public class Violation extends DomainObject<Student> {

    /**
     * 
     */
    private static final long serialVersionUID = 6334552499056006512L;

    private Student student;
    
    private Dict term;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Dict getTerm() {
		return term;
	}

	public void setTerm(Dict term) {
		this.term = term;
	}
    
    
    
}
