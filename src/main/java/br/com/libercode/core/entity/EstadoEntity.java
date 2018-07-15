package br.com.libercode.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "estado")
public class EstadoEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -1;
 
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false,length=2,unique=true)
	private String sigla;
	

	public EstadoEntity(){
		this.ativo = true;
		this.nome = "";
		this.sigla = "";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
