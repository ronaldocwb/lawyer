package br.com.lawyer.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;
import br.com.lawyer.core.entity.enumerated.TipoConvencaoHonorarios;

@Entity
public class ConvencaoHonorarios extends AbstractBaseEntity implements Serializable{
	
	private static final long serialVersionUID = -1345510667899638668L;
	
	@Column(length=120)
	private String nome;
	
	@Enumerated
	private TipoConvencaoHonorarios tpConvencaoHonorarios;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoConvencaoHonorarios getTpConvencaoHonorarios() {
		return tpConvencaoHonorarios;
	}

	public void setTpConvencaoHonorarios(
			TipoConvencaoHonorarios tpConvencaoHonorarios) {
		this.tpConvencaoHonorarios = tpConvencaoHonorarios;
	}
	
}