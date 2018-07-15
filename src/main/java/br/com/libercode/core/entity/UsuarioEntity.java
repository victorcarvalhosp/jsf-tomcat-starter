package br.com.libercode.core.entity;

import br.com.libercode.core.enumerated.Perfil;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usuario")
public class UsuarioEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -1;

	@Column(nullable = false, unique = true)
	private String login;

	@Column(nullable = false)
	private String senha;

	@Column
	@Enumerated(EnumType.STRING)
	private Perfil perfil;

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

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
}

