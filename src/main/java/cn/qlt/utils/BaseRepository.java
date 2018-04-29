package cn.qlt.utils;


import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BaseRepository<T,ID extends Serializable>  extends PagingAndSortingRepository<T, ID>{

	public EntityManager getEntityManager() ;
	
	public List<T> find(String hql, Object... params);
	
	public List<T> findPage(String hql, int pagenumber, int pagesize,
			Object... params);
		
	public T load(ID id) ;
	
	public T findSingle(String column, Object value);

	public Long count(String hql, Object... params);
}
