package br.com.lawyer.core.entity;

import br.com.lawyer.core.base.IUID;
import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Usuario implements IUID<String> {

    @Id
    private String uid;

    private String email;

    private String senha;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = Permissao.class)
    @Enumerated(value = EnumType.STRING)
    private List<Permissao> permissoes;

    @Transient
    private String hashAutenticacao;

    @PrePersist
    private void generateUuid() {
        if (StringUtils.isBlank(this.uid)) {
            this.uid = UUID.randomUUID().toString();
        }
    }

    public String getHashAutenticacao () {
        return hashAutenticacao;
    }

    public void setHashAutenticacao (String hashAutenticacao) {
        this.hashAutenticacao = hashAutenticacao;
    }

    public void setUid (String uuid) {
        this.uid = uuid;
    }

    public String getUid () {
        return uid;
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
