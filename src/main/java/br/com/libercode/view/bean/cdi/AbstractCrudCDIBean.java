package br.com.libercode.view.bean.cdi;

import br.com.libercode.core.bo.AbstractCrudBO;
import br.com.libercode.core.enumerated.Perfil;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Instance;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public abstract class AbstractCrudCDIBean<T> extends AbstractCDIBean {

	private static final long serialVersionUID = 1133484976678049051L;

	protected List<T> lista;

	protected LazyDataModel<T> listaLazy;
	protected T entidade;
	protected T pesquisa;

	protected int tabAtiva = 0;

	@Inject
	private Instance<AbstractCrudBO<T>> boInstance;

	@PreDestroy
	public void handleMessageLeakFixedCDI11() {
		AbstractCrudBO<T> bean = getBo();
		boInstance.destroy(bean); // added in CDI 1.1
	}

	@PostConstruct
	@SuppressWarnings("unchecked")
	protected void postConstruct() {
		limpar();
		configurarEntidadeFlash();
		pesquisar();
	}

	protected void configurarEntidadeFlash() {
		flash().setRedirect(true);
		if ((T) flash().get("entidade") != null) {
			entidade = (T) flash().get("entidade");
			carregarParaEdicao(entidade);
		}
	}

	public abstract void limpar();

	public abstract void limparCadastro();

	public abstract void pesquisar();

	public void salvar() {
		try {
			executarSalvar();
			pesquisar();
		} catch (Exception e) {
			e.printStackTrace();
			exibirMensagemErro("Ocorreu algum erro");
		}
	}

	protected void executarSalvar() {
		getBo().salvar(getEntidade());
		limparCadastro();
		pesquisar();
		exibirMensagemSucesso("Salvo com sucesso");
	}

	public String salvarComRedirecionamento() {
		try {
			getBo().salvar(entidade);
			limparEntidadeFlashEManterMensagens();
			return "listaLazy.xhtml?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void carregarParaEdicao(T entidade) {
		this.entidade = entidade;
	}

	protected void limparEntidadeFlashEManterMensagens() {
		limparEntidadeFlash();
		manterMensagensNaProximaTela();
	}

	protected void manterMensagensNaProximaTela() {
		Flash flash = flash();
		flash.setKeepMessages(true);
		flash.setRedirect(true);
	}

	private void limparEntidadeFlash() {
		flash().put("entidade", null);
	}

	public String cancelar() {
		limparEntidadeFlash();
		limpar();
		return "listaLazy.xhtml?faces-redirect=true";
	}

	public void excluir(T entidade) {
		this.entidade = entidade;
		excluir();
	}

	public void excluir() {
		try {
			getBo().desativar(getEntidade());
			exibirMensagemSucesso("Removido com sucesso");
			limparCadastro();
			pesquisar();
		} catch (Exception e) {
			exibirMensagemErro("Um erro inesperado ocorreu, por favor, entre em contato");
			e.printStackTrace();
		}
	}

	public LazyDataModel<T> getListaLazy() {
		return listaLazy;
	}

	public void setListaLazy(LazyDataModel<T> listaLazy) {
		this.listaLazy = listaLazy;
	}

	public T getEntidade() {
		return entidade;
	}

	public void setEntidade(T entidade) {
		this.entidade = entidade;
	}

	public T getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(T pesquisa) {
		this.pesquisa = pesquisa;
	}

	public int getTabAtiva() {
		return tabAtiva;
	}

	public void setTabAtiva(int tabAtiva) {
		this.tabAtiva = tabAtiva;
	}

	public boolean isMostrarNotificacoesCompletadas() {
		return mostrarNotificacoesCompletadas;
	}

	public void setMostrarNotificacoesCompletadas(boolean mostrarNotificacoesCompletadas) {
		this.mostrarNotificacoesCompletadas = mostrarNotificacoesCompletadas;
	}

	public List<T> getLista() {
		return lista;
	}

	public void setLista(List<T> lista) {
		this.lista = lista;
	}

	protected AbstractCrudBO<T> getBo() {
		return boInstance.get();
	}

}
