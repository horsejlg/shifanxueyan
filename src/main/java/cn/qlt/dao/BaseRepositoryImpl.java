package cn.qlt.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import cn.qlt.utils.BaseRepository;

@NoRepositoryBean
public class BaseRepositoryImpl<T,ID extends Serializable> extends SimpleJpaRepository<T,ID> implements BaseRepository<T,ID>{
	
	public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManager = em;
		this.domainClass = domainClass;
	}

	private final Class<T> domainClass;
	private final EntityManager entityManager; 

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}


	@Override
	public List<T> find(String hql, Object... params) {
		Query query = entityManager.createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.getResultList();
	}

	@Override
	public List<T> findPage(String hql, int pagenumber, int pagesize, Object... params) {
		Query query = entityManager.createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		query.setFirstResult((pagenumber-1) * pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}
	
	@Override
	public Long count(String hql, Object... params){
		if(hql.trim().toLowerCase().startsWith("from")){
			hql = "select count(0) "+hql;
		}else{
			hql = "select count(0) "+ hql.substring(hql.toLowerCase().indexOf("from"));
		}
		Query query = entityManager.createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return (Long) query.getSingleResult();
	}


	@Override
	public T load(ID id) {
		return (T) entityManager.find(currntType(), id);
	}

	@Override
	public T findSingle(String column, Object value) {
		return (T) entityManager.createQuery(String.format("from %s where %s = ?", currntType().getSimpleName(),column)).setParameter(0, value).getSingleResult();
	}

	private Class currntType() {
		return domainClass;
	}


}
