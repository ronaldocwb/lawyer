package br.com.lawyer.core.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.apache.commons.lang.StringUtils;

import br.com.lawyer.core.base.IUID;
import br.com.lawyer.core.entity.common.Endereco;

public class Empresa implements IUID<String> {
	
	@Id
    private String uid;
	private String razaoSocial;
	private String nomeFantasia;
	private String cnpj;
	private List<Telefone> telefones;
	private List<Endereco> enderecos;
	private List<Responsavel> responsaveis;
	
	
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
