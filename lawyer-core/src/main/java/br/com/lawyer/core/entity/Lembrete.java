package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Deividi
 * @since 22/10/2013
 */
@Entity
public class Lembrete extends AbstractBaseEntity {

    @ManyToOne
    private Usuario usuario;

    @Column (length = 1024)
    private String texto;

    private boolean finalizado;

    public Usuario getUsuario () {
        return usuario;
    }

    public void setUsuario (Usuario usuario) {
        this.usuario = usuario;
    }

    public String getTexto () {
        return texto;
    }

    public void setTexto (String texto) {
        this.texto = texto;
    }

    public boolean isFinalizado () {
        return finalizado;
    }

    public void setFinalizado (boolean finalizado) {
        this.finalizado = finalizado;
    }
}
