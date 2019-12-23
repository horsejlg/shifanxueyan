package cn.qlt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.INTEGER) 
@DiscriminatorValue("2")
public class Discipline extends Violation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7312567272826653829L;
	
	@Column(name="createTime")
	private Date time;
	
	
	@Column(length=60,nullable=false)
	private String behavior;
	
	
	@Column(length=1024,nullable=false)
	private String description;

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getBehavior() {
		return behavior;
	}

	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
