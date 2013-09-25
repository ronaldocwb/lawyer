package br.com.lawyer.core.entity.common;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Telefone implements Serializable {
	
	private static final long serialVersionUID = 8490402582329057874L;
	
	public Telefone() {
	}
	
	public Telefone(Integer codPais, Integer codRegiao, Long numero) {
		this.codPais = codPais;
		this.codRegiao = codRegiao;
		this.numero = numero;
	}
	
	public Telefone(Integer codRegiao, Long numero) {
		this.codPais = 55;
		this.codRegiao = codRegiao;
		this.numero = numero;
	}
	
	Integer codPais;
	Integer codRegiao;
	Long numero;
}
