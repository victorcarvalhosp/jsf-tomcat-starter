package br.com.libercode.core.exceptions;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class EmailJaExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	public void adicionarMensagemErroNaTela() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
				"E-mail já existe", "Este e-mail já está cadastrado para outro usuário do sistema"));
	}

}
