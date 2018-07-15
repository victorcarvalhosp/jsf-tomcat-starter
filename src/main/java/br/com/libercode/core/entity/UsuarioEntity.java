package br.com.libercode.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "usuario")
public class UsuarioEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -1;

	@Column(nullable = false, unique = true)
	private String login;

	@Column(nullable = false)
	private String senha;

	@ManyToOne
	private GrupoEntity grupo;

	public UsuarioEntity() {
		this.ativo = true;
		this.login = "";
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public GrupoEntity getGrupo() {
		return grupo;
	}

	public void setGrupo(GrupoEntity grupo) {
		this.grupo = grupo;
	}

}

