package br.com.lawyer.core.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;

@Entity
public class Advogado extends AbstractBaseEntity implements Serializable {

	private static final long serialVersionUID = 8860808843851913915L;
	
	@OneToOne(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	private Pessoa pessoa;
	
	@Column(length=20)
	private String numeroOAB;

	public Advogado() {
	}
	
	public Advogado(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public String getNumeroOAB() {
		return numeroOAB;
	}

	public void setNumeroOAB(String numeroOAB) {
		this.numeroOAB = numeroOAB;
	}
}
