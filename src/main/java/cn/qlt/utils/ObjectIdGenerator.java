package cn.qlt.utils;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.AbstractUUIDGenerator;
import org.hibernate.id.Configurable;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.springframework.util.StringUtils;

public class ObjectIdGenerator extends AbstractUUIDGenerator implements Configurable{

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		if(object instanceof ManagedIdentityDomainObject){
			if(!StringUtils.isEmpty(((ManagedIdentityDomainObject)object).getId())){
				return ((ManagedIdentityDomainObject)object).getId();
			}
		}
		return ObjectId.get().toString();
	}

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		
	}

}
