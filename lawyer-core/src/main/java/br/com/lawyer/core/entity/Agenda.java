package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;

import javax.persistence.Column;
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

    @Column(nullable = false)
    private String nome;

    private String descricao;

    @ManyToOne(optional = false)
    private Usuario usuario;

    @OneToMany
    private List<Evento> eventos;

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

    public Usuario getUsuario () {
        return usuario;
    }

    public void setUsuario (Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Evento> getEventos () {
        return eventos;
    }

    public void setEventos (List<Evento> eventos) {
        this.eventos = eventos;
    }
}
