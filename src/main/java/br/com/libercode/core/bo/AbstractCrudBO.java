package br.com.libercode.core.bo;

import br.com.libercode.core.dao.AbstractCrudDAO;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import javax.annotation.PreDestroy;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractCrudBO<E> implements Serializable {

	private static final long serialVersionUID = 5999897018155072317L;

	@Inject
	protected Instance<AbstractCrudDAO<E>> daoInstance;
	
	protected Class<E> clazz;
	
	@Transactional
	public void salvar(E entidade) {
		if (isPersistido(entidade)) {
			atualizar(entidade);
		} else {
			cadastrar(entidade);
		}
	}
	
	public boolean isPersistido(E entidade) {
		Long id = null;
		try {
			id = (Long) entidade.getClass().getMethod("getId").invoke(entidade);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id != null;
	}

	@Transactional
	public void cadastrar(E entity) {
		getDao().cadastrar(entity);
	}
	
	@Transactional
	public void cadastrarSemSetarDadosRegistro(E entity) {
		getDao().cadastrar(entity);
	}
	
	@Transactional
	public E salvarComRetorno(E entidade) {
		if (isPersistido(entidade)) {
			return entidade;
		} else {
			return cadastrarComRetorno(entidade);
		}
	}
	
	@Transactional
	public E cadastrarComRetorno(E entity) {
		return getDao().cadastrarComRetorno(entity);
	}
	
	@Transactional
	public void atualizar(E entity) {
		getDao().atualizar(entity);
	}
	
	@Transactional
	public void excluir(E entity) {
		getDao().excluir(entity);
	}
	
	@Transactional
	public void desativarPorId(Long id) {
		E entity = getDao().buscarPorId(id);
		getDao().desativar(entity);
	}
	
	@Transactional
	public void desativar(E entity) {
		getDao().desativar(entity);
	}

	public List<E> buscarTodos() {
		return getDao().listarTodos();
	}
	
	public E buscarPorId(Object id) {
		return getDao().buscarPorId(id);
	}
	
	public int total(E pesquisa){
		return getDao().total(pesquisa);
	}
	
	public List<E> buscar(E pesquisa,int startingAt, int maxPerPage, String sortField, String sortOrder){
		return getDao().buscar(pesquisa,
				startingAt, maxPerPage, sortField, sortOrder);
	}
	

	public List<E> buscarTodos(E pesquisa) {
		return getDao().buscarTodos(pesquisa);
	}

	public AbstractCrudDAO<E> getDao() {
		return daoInstance.get();
	}

	@SuppressWarnings("unchecked")
    protected Class<E> getClassType() {
        if (clazz == null) {
        	ParameterizedType parameterizedType = (ParameterizedType) getClass().getSuperclass().getGenericSuperclass();
            clazz = (Class<E>) parameterizedType.getActualTypeArguments()[0];
        }
        return clazz;
    }

    @PreDestroy
	private void handleMessageLeakFixedCDI11(){
		AbstractCrudDAO<E> bean = getDao();
		daoInstance.destroy(bean); // added in CDI 1.1
	}

}
