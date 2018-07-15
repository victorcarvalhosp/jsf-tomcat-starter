package br.com.libercode.view.bean;

import br.com.libercode.core.bo.UsuarioBO;
import br.com.libercode.core.entity.UsuarioEntity;
import br.com.libercode.view.bean.cdi.AbstractCDIBean;
import br.com.libercode.view.navigation.Router;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Named("loginBean")
@SessionScoped
public class LoginBean extends AbstractCDIBean {

	private static final long serialVersionUID = 1L;
	private static final String LOGIN_PAGE = "/redirecionarLogin.xhtml?faces-redirect=true";
	private String email;
	private String senha;

	@Inject
	private Router router;

	@Inject
	private UsuarioBO usuarioBo;

    @PostConstruct
	public void postConstruct() {
	}

	public void doLogin() {
			UsuarioEntity usuario = usuarioBo.findByLoginAndPassword(email, senha);
			if(usuario != null){
				colocarUsuarioNaSessao(usuario);
				router.redirecionarRestritoInicio();
			}else{
				exibirMensagemErro("Login ou Senha Incorretos");
			}
	}


	private void colocarUsuarioNaSessao(UsuarioEntity usuarioSelecionado) {
		usuarioBO.colocarUsuarioCondominioNaSessao(usuarioSelecionado);
	}

	public void recuperarSenha() {
	}

	public void logout() {
		invalidateSession();
		router.redirecionarLogin();
	}

	private void invalidateSession() {
		((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession()
				.setAttribute("usuarioLogado", null);
		((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession()
				.setAttribute("condominioCarregado", null);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		postConstruct();
	}

	private String redirectLogout() {
		return LOGIN_PAGE;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}