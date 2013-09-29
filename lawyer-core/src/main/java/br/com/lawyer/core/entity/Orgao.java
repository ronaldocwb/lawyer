package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Orgao extends AbstractBaseEntity implements Serializable {

    private static final long serialVersionUID = -1345510667899638668L;

    @Column (length = 120)
    private String nome;

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

}