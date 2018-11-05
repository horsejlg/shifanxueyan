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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ManagedIdentityDomainObject other = (ManagedIdentityDomainObject) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
