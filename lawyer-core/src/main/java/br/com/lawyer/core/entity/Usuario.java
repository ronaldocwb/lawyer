package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;
import br.com.lawyer.core.entity.enumerated.Permissao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Usuario extends AbstractBaseEntity implements Serializable {

    private static final long serialVersionUID = -3038405341454481689L;

    @Column (length = 200)
    private String email;

    @Column (length = 128)
    private String senha;

    @ElementCollection (fetch = FetchType.EAGER, targetClass = Permissao.class)
    @Enumerated (value = EnumType.STRING)
    @JoinColumn (columnDefinition = "Permissao")
    private List<Permissao> permissoes;

    public String getSenha () {
        return senha;
    }

    public void setSenha (String senhaCriptografada) {
        this.senha = senhaCriptografada;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public List<Permissao> getPermissoes () {
        return permissoes;
    }

    public void setPermissoes (List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

}
