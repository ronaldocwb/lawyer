package br.com.lawyer.core.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;

@Entity
public class Contrato extends AbstractBaseEntity implements Serializable{
	
	private static final long serialVersionUID = -1345510489899638668L;
	
	@Column(length=200)
	private String nome;
	
	@ManyToOne
	private Empresa empresa;
	
	@ElementCollection(fetch=FetchType.EAGER)
	private List<ConvencaoHonorarios> convencaoHonorarios;
	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public List<ConvencaoHonorarios> getConvencaoHonorarios() {
		return convencaoHonorarios;
	}
	
	public void addConvencaoHonorarios(ConvencaoHonorarios convencaoHonorarios) {
		if(this.convencaoHonorarios == null){
			this.convencaoHonorarios = new ArrayList<ConvencaoHonorarios>();
		}
		
		this.convencaoHonorarios.add(convencaoHonorarios);
	}

	public void removeConvencaoHonorarios(ConvencaoHonorarios convencaoHonorarios) {
		if(this.convencaoHonorarios == null){
			return;
		}
		
		this.convencaoHonorarios.remove(convencaoHonorarios);
	}
}