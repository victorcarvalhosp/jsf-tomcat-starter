package br.com.libercode.core.bo;

import br.com.libercode.core.dao.EstadoDAO;
import br.com.libercode.core.entity.EstadoEntity;

public class EstadoBO extends AbstractCrudBO<EstadoEntity> {

	private static final long serialVersionUID = 5038744935006859141L;
	
	public EstadoEntity findBySigla(String sigla) {
		return getDao().findBySigla(sigla);
	}
	
	@Override
	public EstadoDAO getDao() {
		return (EstadoDAO) super.getDao();
	}
}
