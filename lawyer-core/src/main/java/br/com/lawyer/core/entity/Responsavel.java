package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Representa o responsável por um setor especifico da empresa
 *
 * @author Ronaldo
 */
@Entity
public class Responsavel extends AbstractBaseEntity implements Serializable {

    private static final long serialVersionUID = -4486187479124482772L;

    @ManyToOne(optional = true)
    private Pessoa pessoa;

    @ManyToOne(optional = true)
    private Setor setor;

    public Pessoa getPessoa () {
        return pessoa;
    }

    public void setPessoa (Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Setor getSetor () {
        return setor;
    }

    public void setSetor (Setor setor) {
        this.setor = setor;
    }
}
