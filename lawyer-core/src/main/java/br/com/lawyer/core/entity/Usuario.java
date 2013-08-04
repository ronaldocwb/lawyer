package br.com.lawyer.core.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator (name = "hibernate-uuid", strategy = "uuid2")
    private String uuid;

    private String email;

    private String senha;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = Permissao.class)
    @Enumerated(value = EnumType.ORDINAL)
    private List<Permissao> permissoes;

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
