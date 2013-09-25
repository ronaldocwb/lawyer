package br.com.lawyer.core.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Transient;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;
import br.com.lawyer.core.entity.enumerated.Permissao;

@Entity
public class Usuario extends AbstractBaseEntity implements Serializable {
	
	private static final long serialVersionUID = -3038405341454481689L;

	@Column(length=200)
    private String email;
	
	@Column(length=30)
    private String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private List<Permissao> permissoes;

    @Transient
    private String hashAutenticacao;

    public String getHashAutenticacao () {
        return hashAutenticacao;
    }

    public void setHashAutenticacao (String hashAutenticacao) {
        this.hashAutenticacao = hashAutenticacao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senhaCriptografada) {
        this.senha = senhaCriptografada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Permissao> getPermissoes () {
        return permissoes;
    }

    public void setPermissoes (List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

}
