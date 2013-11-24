package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;
import br.com.lawyer.core.entity.enumerated.Permissao;

import javax.persistence.*;
import java.util.List;

@Entity
public class Usuario extends AbstractBaseEntity {

    @Column (length = 200)
    private String email;

    @Column (length = 128)
    private String senha;

    @ManyToOne
    private Advocacia advocacia;

    @OneToOne
    private Pessoa pessoa;

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

    public Advocacia getAdvocacia () {
        return advocacia;
    }

    public void setAdvocacia (Advocacia advocacia) {
        this.advocacia = advocacia;
    }

    public Pessoa getPessoa () {
        return pessoa;
    }

    public void setPessoa (Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
