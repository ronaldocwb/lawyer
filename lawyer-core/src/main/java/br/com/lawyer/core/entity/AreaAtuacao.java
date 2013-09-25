package br.com.lawyer.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;

@Entity
public class AreaAtuacao extends AbstractBaseEntity implements Serializable {
	
	private static final long serialVersionUID = 5093446877731548419L;
	
	@Column(length=100)
	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
