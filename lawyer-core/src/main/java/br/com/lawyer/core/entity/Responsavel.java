package br.com.lawyer.core.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;

/**
 * Representa o responsável por um setor especifico da empresa
 * 
 * @author Ronaldo
 */
@Entity
public class Responsavel extends AbstractBaseEntity implements Serializable{
	
	private static final long serialVersionUID = -4486187479124482772L;

	@ManyToOne
	private Pessoa pessoa;
	
	@ManyToOne
	private Empresa empresa;
	
	@ManyToOne
	private Setor setor;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
}
