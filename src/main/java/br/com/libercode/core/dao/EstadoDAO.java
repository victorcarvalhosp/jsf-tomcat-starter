package br.com.libercode.core.dao;

import br.com.libercode.core.entity.EstadoEntity;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class EstadoDAO extends AbstractCrudDAO<EstadoEntity> {

	private static final long serialVersionUID = 7608907365801909171L;

	public List<EstadoEntity> buscar(EstadoEntity pesquisa, int startingAt, int maxPerPage, String sortField,
			String sortOrder) {

		StringBuilder hql = new StringBuilder();
		hql.append("FROM EstadoEntity tab ");
		hql.append("LEFT JOIN FETCH tab.autor ");
		preencherWhereBusca(hql);
		hql.append("ORDER BY " + sortField + " " + sortOrder);

		TypedQuery<EstadoEntity> q = entityManager.createQuery(hql.toString(), EstadoEntity.class);

		preencherParametrosQueryBusca(pesquisa, q);

		q.setMaxResults(maxPerPage);
		q.setFirstResult(startingAt);
		return q.getResultList();
	}

	private void preencherWhereBusca(StringBuilder hql) {
		hql.append(
				"WHERE lower(tab.nome) LIKE lower(:nome) AND lower(tab.sigla) LIKE lower(:sigla) AND (tab.ativo = :ativo)");
	}

	private void preencherParametrosQueryBusca(EstadoEntity pesquisa, Query q) {
		q.setParameter("nome", "%" + pesquisa.getNome() + "%");
		q.setParameter("sigla", "%" + pesquisa.getSigla() + "%");
		q.setParameter("ativo", pesquisa.getAtivo());
	}

	public int total(EstadoEntity pesquisa) {
		StringBuilder hql = new StringBuilder();
		hql.append("SELECT count(*) from EstadoEntity tab ");
		preencherWhereBusca(hql);

		TypedQuery<Long> q = entityManager.createQuery(hql.toString(), Long.class);
		preencherParametrosQueryBusca(pesquisa, q);
		return q.getSingleResult().intValue();
	}
	
	public EstadoEntity findBySigla(String sigla) {
		TypedQuery<EstadoEntity> query = 
				entityManager.createQuery("SELECT tab from EstadoEntity where lower(tab.sigla) = lower(?1)", EstadoEntity.class)
				.setParameter(1, sigla);
		return getSingleOrNull(query);
	}

}
