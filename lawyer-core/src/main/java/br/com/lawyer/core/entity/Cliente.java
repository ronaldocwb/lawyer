package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * @author Deividi
 * @since 22/11/2013
 */
@Entity
public class Cliente extends AbstractBaseEntity {

    @OneToOne(orphanRemoval = false, optional = true)
    private Empresa empresa;

    @OneToOne(orphanRemoval = false, optional = true)
    private Pessoa pessoa;

    public Cliente() {}

    public Cliente (Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Cliente (Empresa empresa) {
        this.empresa = empresa;
    }

    public Empresa getEmpresa () {
        return empresa;
    }

    public void setEmpresa (Empresa empresa) {
        this.empresa = empresa;
    }

    public Pessoa getPessoa () {
        return pessoa;
    }

    public void setPessoa (Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
