package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;
import br.com.lawyer.core.entity.common.Email;
import br.com.lawyer.core.entity.common.Endereco;
import br.com.lawyer.core.entity.common.Telefone;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
@JsonIgnoreProperties (ignoreUnknown = true)
public class Pessoa extends AbstractBaseEntity {

    @Column (length = 120, nullable = false)
    private String nome;

    @Embedded
    private Documento documento;

    @ManyToOne(optional = true)
    private Empresa empresa;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Telefone> telefones;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Email> emails;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Endereco> enderecos;

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public Documento getDocumento () {
        return documento;
    }

    public void setDocumento (Documento documento) {
        this.documento = documento;
    }

    public Empresa getEmpresa () {
        return empresa;
    }

    public void setEmpresa (Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Telefone> getTelefones () {
        return telefones;
    }

    public void setTelefones (List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Endereco> getEnderecos () {
        return enderecos;
    }

    public void setEnderecos (List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Email> getEmails () {
        return emails;
    }

    public void setEmails (List<Email> emails) {
        this.emails = emails;
    }
    
}
