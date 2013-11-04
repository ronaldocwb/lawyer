package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Atividade extends AbstractBaseEntity {

    @ManyToOne
    private Usuario usuario;

    @Temporal (TemporalType.DATE)
    private Date data;

    //TODO transformar em UserType, copiar do VeerePatterns
    private boolean ativo;

    public Usuario getUsuario () {
        return usuario;
    }

    public void setUsuario (Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isAtivo () {
        return ativo;
    }

    public void setAtivo (boolean ativo) {
        this.ativo = ativo;
    }

    public Date getData () {
        return data;
    }

    public void setData (Date data) {
        this.data = data;
    }

}
