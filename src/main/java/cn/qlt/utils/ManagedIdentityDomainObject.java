package cn.qlt.utils;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class ManagedIdentityDomainObject<T extends ManagedIdentityDomainObject<T>> extends DomainObject<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2670418277180033976L;
	
	@GenericGenerator(name="idGenerator", strategy="cn.qlt.utils.ObjectIdGenerator") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator="idGenerator")
	@Id
	@Column(length=24)
	protected String id;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
