package cn.qlt.domain;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.qlt.utils.DomainObject;
import cn.qlt.utils.ManagedIdentityDomainObject;

@Entity
@Table(name="violation")
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.INTEGER) 
@DiscriminatorValue("0")
public class Violation extends ManagedIdentityDomainObject<Violation> {

    /**
     * 
     */
    private static final long serialVersionUID = 6334552499056006512L;

    @ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
    @JoinColumn(name="student",nullable=false)
    private Student student;
    
    
    @ManyToOne(cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
    @JoinColumn(name="term",nullable=false)
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
