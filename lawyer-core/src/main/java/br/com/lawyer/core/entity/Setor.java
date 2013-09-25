package br.com.lawyer.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;

@Entity
public class Setor extends AbstractBaseEntity implements Serializable{
	
	private static final long serialVersionUID = -1345510669349638668L;
	
	@Column(length=200)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
