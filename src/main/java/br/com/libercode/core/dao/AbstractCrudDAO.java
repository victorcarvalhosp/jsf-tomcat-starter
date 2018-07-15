package br.com.libercode.core.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractCrudDAO<E> implements Serializable {

	@Inject
	protected EntityManager entityManager;

	protected Class<E> clazz;

	private static final long serialVersionUID = -3513187667326549082L;

	public void cadastrar(E entity) {
		entityManager.persist(entity);
	}
	
	public E cadastrarComRetorno(E entity) {
		entity = entityManager.merge(entity);
		entityManager.flush();
		return entity;
	}
	
	public void atualizar(E entity) {
		entityManager.merge(entity);
	}

	public void excluir(E entity) {
		entityManager.remove(entityManager.merge(entity));
	}

	public void desativar(E entity) {
		try {
			entity.getClass().getMethod("setAtivo", Boolean.class).invoke(entity, false);
			entityManager.merge(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<E> listarTodos() {
		return entityManager
				.createQuery("from " + getClassType().getSimpleName() + " where ativo = true", getClassType())
				.getResultList();
	}

	public E buscarPorId(Object id) {
		return entityManager.find(getClassType(), id);
	}

	@SuppressWarnings("unchecked")
	public <T> T getSingleOrNull(Query q) {
		try {
			return (T) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public Class<E> getClassType() {
		if (clazz == null) {
			ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
			clazz = (Class<E>) parameterizedType.getActualTypeArguments()[0];
		}
		return clazz;
	}

	@SuppressWarnings("unchecked")
	public List<E> buscar(E pesquisa, int startingAt, int maxPerPage, String sortField, String sortOrder) {
		Query q = entityManager.createQuery("from " + pesquisa.getClass().getName() + " tab left join fetch tab.autor");
		q.setFirstResult(startingAt);
		q.setMaxResults(maxPerPage);
		return q.getResultList();
	}

	public int total(E pesquisa) {
		Long count = 0L;
		Query q = entityManager.createQuery("SELECT count(*) FROM " + pesquisa.getClass().getName());
		count = (Long) q.getSingleResult();
		return count.intValue();
	}
	
	@SuppressWarnings("unchecked")
	public List<E> buscarTodos(E pesquisa) {
		Query q = entityManager.createQuery("from " + pesquisa.getClass().getName() + " tab left join fetch tab.autor");
		return q.getResultList();
	}
}
