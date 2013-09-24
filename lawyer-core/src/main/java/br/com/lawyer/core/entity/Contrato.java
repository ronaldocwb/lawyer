package br.com.lawyer.core.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.apache.commons.lang.StringUtils;

public class Contrato implements Serializable{
	
	private static final long serialVersionUID = -1345510489899638668L;
	
	@Id
    private String uid;
	private String nome;
	private Empresa empresa;
	
	@PrePersist
    private void generateUuid() {
        if (StringUtils.isBlank(this.uid)) {
            this.uid = UUID.randomUUID().toString();
        }
    }
	
	public void setUid (String uuid) {
        this.uid = uuid;
    }

    public String getUid () {
        return uid;
    }
}