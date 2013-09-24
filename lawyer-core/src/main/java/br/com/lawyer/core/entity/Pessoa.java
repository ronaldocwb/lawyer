package br.com.lawyer.core.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.apache.commons.lang.StringUtils;

import br.com.lawyer.core.base.IUID;
import br.com.lawyer.core.entity.common.Endereco;

public class Pessoa implements IUID<String> {
	
	@Id
    private String uid;
	private String nome;
	private Documento documento;
	private Empresa empresa;
	private String email;
	private List<Telefone> telefones;
	private List<Endereco> enderecos;
	
	
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
