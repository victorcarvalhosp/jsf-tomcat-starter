package br.com.libercode.view.navigation;

import br.com.libercode.view.util.JSFUtil;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("router")
@RequestScoped
public class Router implements Serializable {

	private static final long serialVersionUID = 7534881207021173422L;

	private static final String LOGIN = "login.xhtml";
	private static final String ERROR_404 = "404.xhtml";
	private static final String RESTRITO_INICIO = "restrito/index.xhtml";



	public void redirecionarErro404() {
		redirect(ERROR_404);
	}

	public void redirecionarLogin() {
		redirect(LOGIN);
	}

	public void redirecionarRestritoInicio() {
		redirect(RESTRITO_INICIO);
	}

	public String urlRestritoInicio() {
		return JSFUtil.caminhoRedirect(RESTRITO_INICIO);
	}

	public String urlLogin() {
		return JSFUtil.caminhoRedirect(LOGIN);
	}

	private void redirect(String path) {
		JSFUtil.redirect(path);
	}
}