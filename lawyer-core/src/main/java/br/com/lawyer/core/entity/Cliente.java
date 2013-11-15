package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author Deividi
 * @since 31/10/2013
 */
@Entity
public class Cliente extends AbstractBaseEntity {

    private String nome;

    @OneToMany
    @LazyCollection (LazyCollectionOption.FALSE)
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
