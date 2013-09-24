package br.com.lawyer.core.entity.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Embeddable
public class Endereco {
	
	@ManyToOne
	@JoinColumn
	private Municipio municipio;
	
	@Column(length=45)
	private String cep;
	
	@Column(length=255)
	private String logradouro;
	
	@Column(length=100)
	private String numero;
	
	@Column(length=100)
	private String complemento;
	
	@Column(length=100)
	private String bairro;
	
	public Municipio getMunicipio() {
		return this.municipio;
	}
	
	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}
	
	public String getCep() {
		return this.cep;
	}
	
	public void setCep(String endCep) {
		this.cep = endCep;
	}
	
	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String endLogradouro) {
		this.logradouro = endLogradouro;
	}
	
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String endNumero) {
		this.numero = endNumero;
	}
	
	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String endComplemento) {
		this.complemento = endComplemento;
	}
	
	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String endBairro) {
		this.bairro = endBairro;
	}
}
