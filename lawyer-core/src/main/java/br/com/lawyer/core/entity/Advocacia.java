package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author Deividi
 * @since 31/10/2013
 */
@Entity
public class Advocacia extends AbstractBaseEntity {

    private String nome;

    @OneToMany(orphanRemoval = false)
    private List<Usuario> usuarios;

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public List<Usuario> getUsuarios () {
        return usuarios;
    }

    public void setUsuarios (List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
