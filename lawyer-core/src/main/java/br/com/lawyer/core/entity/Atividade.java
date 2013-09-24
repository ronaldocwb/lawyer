package br.com.lawyer.core.entity;

import br.com.lawyer.core.base.IUID;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Atividade implements IUID<String> {

    @Id
    @GeneratedValue(generator = "hibernate-uuid")
    @GenericGenerator (name = "hibernate-uuid", strategy = "uuid2")
    private String uid;

    @ManyToOne
    private Usuario usuario;

    @Temporal(TemporalType.DATE)
    private Date data;



    private boolean ativo;

    public void setUid (String uuid) {
        this.uid = uuid;
    }

    public String getUid () {
        return this.uid;
    }

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
