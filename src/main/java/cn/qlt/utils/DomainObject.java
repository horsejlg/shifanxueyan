package cn.qlt.utils;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class DomainObject<T extends DomainObject<T>> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9212649037132771507L;
	
}
