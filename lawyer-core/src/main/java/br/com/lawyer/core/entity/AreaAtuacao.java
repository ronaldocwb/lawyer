package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class AreaAtuacao extends AbstractBaseEntity {

    @Column (length = 100)
    private String nome;

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }
}
