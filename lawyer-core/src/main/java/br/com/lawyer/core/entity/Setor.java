package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Setor extends AbstractBaseEntity  {

    @Column (length = 200)
    private String nome;

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }
}
