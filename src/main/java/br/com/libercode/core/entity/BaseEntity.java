package br.com.libercode.core.entity;

import br.com.libercode.view.util.JSFUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.faces.context.FacesContext;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	protected static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name="REGISTRO",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date registro;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="AUTOR",referencedColumnName="ID", nullable=true)
	protected UsuarioEntity autor;
	
	@Column(name="ATUALIZACAO",nullable=true)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date atualizacao;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ATUALIZADOR",referencedColumnName="ID", nullable=true)
	protected UsuarioEntity atualizador;
	
	@Column(name = "ATIVO", nullable = false)
	protected Boolean ativo;

	@Transient
	private Boolean selecionado;
	
	@Transient
	protected Long idTemporario;
	
	public BaseEntity() {
		super();
		this.ativo = true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Boolean getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Boolean selecionado) {
		this.selecionado = selecionado;
	}
	
	public Long getIdTemporario() {
		return idTemporario;
	}

	public void setIdTemporario(Long idTemporario) {
		this.idTemporario = idTemporario;
	}
	
	@PrePersist
	public void inicializarDataCriacao() {
		if (registro == null) {
			registro = new Date();
		}
		this.ativo = true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((idTemporario == null) ? 0 : idTemporario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		BaseEntity other = (BaseEntity) obj;
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!obj.getClass().getName().contains(getClass().getName()))
			return false;
		if (id == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.getId()))
			return false;
		if (idTemporario == null) {
			if (other.idTemporario != null)
				return false;
		} else if (!idTemporario.equals(other.idTemporario))
			return false;
		return true;
	}

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	public UsuarioEntity getAutor() {
		return autor;
	}

	public void setAutor(UsuarioEntity autor) {
		this.autor = autor;
	}

	public Date getAtualizacao() {
		return atualizacao;
	}

	public void setAtualizacao(Date atualizacao) {
		this.atualizacao = atualizacao;
	}

	public UsuarioEntity getAtualizador() {
		return atualizador;
	}

	public void setAtualizador(UsuarioEntity atualizador) {
		this.atualizador = atualizador;
	}

}
