package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;

import javax.persistence.*;

@Entity
public class Advogado extends AbstractBaseEntity {

    @OneToOne (fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(unique = true, nullable = false)
    private Pessoa pessoa;

    @Column (length = 20)
    private String numeroOAB;

    public Advogado () {
    }

    public Advogado (Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Pessoa getPessoa () {
        return pessoa;
    }

    public String getNumeroOAB () {
        return numeroOAB;
    }

    public void setNumeroOAB (String numeroOAB) {
        this.numeroOAB = numeroOAB;
    }

    // precisa pra fazer o parse do VO
    public void setPessoa (Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
