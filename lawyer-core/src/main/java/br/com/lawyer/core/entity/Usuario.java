package br.com.lawyer.core.entity;

import org.apache.commons.lang.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
public class Usuario implements Serializable {

    @Id
    private String uuid;

    private String email;

    private String senha;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = Permissao.class)
    @Enumerated(value = EnumType.ORDINAL)
    private List<Permissao> permissoes;

    @PrePersist
    private void generateUuid() {
        if (StringUtils.isBlank(this.uuid)) {
            this.uuid = UUID.randomUUID().toString();
        }
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
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
