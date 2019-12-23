package cn.qlt.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(name="type",discriminatorType=DiscriminatorType.INTEGER) 
@DiscriminatorValue("3")
public class GeneralDiscipline extends Discipline {

    /**
     * 
     */
    private static final long serialVersionUID = -7312567272826653829L;

}
