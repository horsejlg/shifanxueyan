package cn.qlt.utils;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@SuppressWarnings({"rawtypes","hiding","unchecked"})
public class DomainRepository<T extends DomainObject<T>> {

	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public <T> T insert(T t) {
		if (t instanceof ManagedIdentityDomainObject) {
			ManagedIdentityDomainObject obj = (ManagedIdentityDomainObject) t;
			if(obj.getId()==null)
				((ManagedIdentityDomainObject) t).setId(ObjectId.get().toString());
		}
		em.persist(t);
		return t;
	}

	public <T> T merge(T t) {
		if (t instanceof ManagedIdentityDomainObject) {
			ManagedIdentityDomainObject obj = (ManagedIdentityDomainObject) t;
			if(obj.getId()==null)
				return insert(t);
		}
		return em.merge(t);
	}

	public List<T> find(String hql, Object... params) {
		Query query = em.createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i+1, params[i]);
			}
		}
		return query.getResultList();
	}

	public List<T> findPage(String hql, int pagenumber, int pagesize,
			Object... params) {
		Query query = em.createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
		query.setFirstResult(pagenumber * pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}
	
	public <T> T findOne(String name, Object value){
		Query query = em.createQuery(String.format("%s where %s = ?", currntType().getSimpleName(), name));
		query.setParameter(1, value);
		return (T) query.getSingleResult();
	}
	
	public Long count(String hql, Object... params){
		if(hql.trim().toLowerCase().startsWith("from")){
			hql = "select count(0) "+hql;
		}else{
			hql = "select count(0) "+ hql.substring(hql.toLowerCase().indexOf("from"));
		}
		Query query = em.createQuery(hql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
		return (Long) query.getSingleResult();
	}
	
	public void delete(String id){
		em.remove(load(id));
		em.flush();
	}

	public <T> T load(String id) {
		return (T) em.find(currntType(), id);
	}

	private Class currntType() {
		return (Class) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

}
