package br.com.libercode.view.bean;

import br.com.libercode.core.bo.EstadoBO;
import br.com.libercode.core.entity.EstadoEntity;
import br.com.libercode.view.bean.cdi.AbstractCrudCDIBean;
import org.omnifaces.cdi.ViewScoped;

import javax.inject.Named;

@ViewScoped
@Named("estadoBean")
public class EstadoBean extends AbstractCrudCDIBean<EstadoEntity> {

	private static final long serialVersionUID = 1L;

	public void limpar() {
		limparCadastro();
		limparPesquisa();
	}

	public void limparCadastro() {
		setEntidade(new EstadoEntity());
	}

	public void pesquisar() {
//		setListaLazy(new GenericLazyListCDI<EstadoEntity>(EstadoEntity.class,
//				getBo(), getPesquisa()));
		this.lista = getBo().buscarTodos(pesquisa);
	}

	public void limparPesquisa() {
		setPesquisa(new EstadoEntity());
	}

	@Override
	protected EstadoBO getBo() {
		return (EstadoBO) super.getBo();
	}

}
