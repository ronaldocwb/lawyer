package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;
import br.com.lawyer.core.entity.common.Email;
import br.com.lawyer.core.entity.common.Endereco;
import br.com.lawyer.core.entity.common.Telefone;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Empresa extends AbstractBaseEntity {

    @Column (length = 120)
    private String razaoSocial;

    @Column (length = 120)
    private String nomeFantasia;

    @Column (length = 15)
    private String cnpj;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Telefone> telefones;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Endereco> enderecos;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Email> emails;

    @OneToMany(cascade={CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Responsavel> responsaveis;

    private Boolean cliente = Boolean.FALSE;

    public String getRazaoSocial () {
        return razaoSocial;
    }

    public void setRazaoSocial (String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia () {
        return nomeFantasia;
    }

    public void setNomeFantasia (String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj () {
        return cnpj;
    }

    public void setCnpj (String cnpj) {
        this.cnpj = cnpj;
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

    public List<Responsavel> getResponsaveis () {
        return responsaveis;
    }

    public void setResponsaveis (List<Responsavel> responsaveis) {
        this.responsaveis = responsaveis;
    }

    public List<Email> getEmails () {
        return emails;
    }

    public void setEmails (List<Email> emails) {
        this.emails = emails;
    }

    public Boolean getCliente () {
        return cliente;
    }

    public void setCliente (Boolean cliente) {
        this.cliente = cliente;
    }
}
