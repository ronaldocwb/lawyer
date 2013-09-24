package br.com.lawyer.core.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.lawyer.core.base.IUID;
import br.com.lawyer.core.entity.base.AbstractBaseEntity;

@Entity
public class Atividade extends AbstractBaseEntity implements IUID<String> {

	private static final long serialVersionUID = -3614535508787511308L;

	@ManyToOne
    private Usuario usuario;

    @Temporal(TemporalType.DATE)
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
}
