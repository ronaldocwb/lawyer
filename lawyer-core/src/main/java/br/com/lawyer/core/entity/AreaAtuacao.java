package br.com.lawyer.core.entity;

import javax.persistence.Id;

import br.com.lawyer.core.base.IUID;

public class AreaAtuacao implements IUID<String> {
	
	@Id
    private String uid;
	private String nome;
	
	@Override
	public String getUid() {
		return uid;
	}
}
