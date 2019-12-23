package cn.qlt.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.INTEGER) 
@DiscriminatorValue("4")
public class SeriousDisciplinary extends GeneralDiscipline {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8631383846200552128L;
	
	

}
