package cn.qlt.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="violation")
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.INTEGER) 
@DiscriminatorValue("1")
public class NotPass extends Violation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3404538985685416326L;
	
	private Dict subject;

	public Dict getSubject() {
		return subject;
	}

	public void setSubject(Dict subject) {
		this.subject = subject;
	}

}
