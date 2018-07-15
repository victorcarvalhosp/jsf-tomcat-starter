package br.com.libercode.view.bean.cdi;

import br.com.libercode.core.bo.UsuarioBO;
import br.com.libercode.core.entity.UsuarioEntity;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Set;

public abstract class AbstractCDIBean implements Serializable {

	private static final long serialVersionUID = -5544450766298460329L;

	protected boolean mostrarNotificacoesCompletadas;
	
	protected UsuarioEntity usuarioLogado;

	@Inject
	protected UsuarioBO usuarioBO;

	public UsuarioEntity getUsuarioLogado() {
		if (usuarioLogado == null) {
            atualizarUsuarioLogado();
        }
		return usuarioLogado;
	}

    protected void atualizarUsuarioLogado() {
        usuarioLogado = (UsuarioEntity) ((HttpServletRequest) externalContext()
                .getRequest()).getSession().getAttribute("usuarioLogado");
    }

    @SuppressWarnings("unchecked")
	public Set<String> getRecursosAcessoLogado() {
		return (Set<String>) ((HttpServletRequest) externalContext()
					.getRequest()).getSession().getAttribute("recursos");
	}

	public static FacesContext facesContext() {
		return FacesContext.getCurrentInstance();
	}

	public static ExternalContext externalContext() {
		return facesContext().getExternalContext();
	}

	public static Flash flash() {
		return externalContext().getFlash();
	}

	public void exibirMensagemSucesso(String titulo) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, ""));
	}

	public void exibirMensagemErro(String titulo) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, titulo, ""));
	}

	public void exibirMensagemAviso(String titulo) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, titulo, ""));
	}


}
