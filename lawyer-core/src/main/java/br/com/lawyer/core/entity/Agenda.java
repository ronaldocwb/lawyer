package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;
import br.com.lawyer.core.entity.enumerated.TipoAcesso;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author Deividi
 * @since 08/12/2013
 */
@Entity
public class Agenda extends AbstractBaseEntity {

    private String nome;

    private String descricao;

    @ManyToOne
    private Usuario usuarioOwner;

    @OneToMany
    private List<Evento> eventos;

    private TipoAcesso tipoAcesso;

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public String getDescricao () {
        return descricao;
    }

    public void setDescricao (String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuarioOwner () {
        return usuarioOwner;
    }

    public void setUsuarioOwner (Usuario usuario) {
        this.usuarioOwner = usuario;
    }

    public List<Evento> getEventos () {
        return eventos;
    }

    public void setEventos (List<Evento> eventos) {
        this.eventos = eventos;
    }

    public TipoAcesso getTipoAcesso () {
        return tipoAcesso;
    }

    public void setTipoAcesso (TipoAcesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }
}
