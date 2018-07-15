package br.com.libercode.core.dao;

import org.apache.deltaspike.core.api.provider.BeanProvider;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;

public class ConverterPersistenceUtility<E> {

	protected EntityManager em;
	protected Class<E> clazz;
	
	public ConverterPersistenceUtility() {
		this.em = BeanProvider.getContextualReference(EntityManager.class, false);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public E buscarPorId(Class clazz, Object id) {
		return (E) em.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
    public Class<E> getClassType() {
        if (clazz == null) {
            ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
            clazz = (Class<E>) parameterizedType.getActualTypeArguments()[0];
        }
        return clazz;
    }
	
}
