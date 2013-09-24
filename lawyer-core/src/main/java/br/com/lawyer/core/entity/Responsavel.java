package br.com.lawyer.core.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import org.apache.commons.lang.StringUtils;

/**
 * Representa o responsável por um setor especifico da empresa
 * 
 * @author Ronaldo
 */
public class Responsavel implements Serializable{
	
	private static final long serialVersionUID = -4486187479124482772L;

	@Id
    private String uid;
	
	@ManyToOne
	private Pessoa pessoa;
	
	@ManyToOne
	private Empresa empresa;
	private Setor setor;
	
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
