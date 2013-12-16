package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;
import br.com.lawyer.core.entity.enumerated.TipoContato;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * @author Deividi
 * @since 22/11/2013
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize (include = JsonSerialize.Inclusion.NON_NULL)
public class Contato extends AbstractBaseEntity implements Serializable {

    @OneToOne(orphanRemoval = false, optional = true, cascade = CascadeType.ALL)
    private Empresa empresa;

    @OneToOne(orphanRemoval = false, optional = true, cascade = CascadeType.ALL)
    private Pessoa pessoa;

    @OneToOne(orphanRemoval = false, optional = true, cascade = CascadeType.ALL)
    private Usuario usuario;

    @Enumerated
    private TipoContato tipoContato = TipoContato.CONTATO;

    public Contato () {}

    public Contato (Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Contato (Empresa empresa) {
        this.empresa = empresa;
    }

    public Contato (Usuario usuario) {
        this.usuario = usuario;
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

    public TipoContato getTipoContato () {
        return tipoContato;
    }

    public void setTipoContato (TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    public Usuario getUsuario () {
        return usuario;
    }

    public void setUsuario (Usuario usuario) {
        this.usuario = usuario;
    }
}
