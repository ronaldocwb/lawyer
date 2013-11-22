package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;
import br.com.lawyer.core.entity.common.Email;
import br.com.lawyer.core.entity.common.Endereco;
import br.com.lawyer.core.entity.common.Telefone;

import javax.persistence.*;
import java.util.List;

@Entity
public class Pessoa extends AbstractBaseEntity {

    @Column (length = 120)
    private String nome;

    @Embedded
    private Documento documento;

    @ManyToOne(optional = true)
    private Empresa empresa;

    @Column (length = 200)
    private String email;

    @ElementCollection
    private List<Telefone> telefones;

    @ElementCollection
    private List<Email> emails;

    @ElementCollection
    private List<Endereco> enderecos;

    private Boolean cliente = Boolean.FALSE;

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

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
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

    public Boolean getCliente () {
        return cliente;
    }

    public void setCliente (Boolean cliente) {
        this.cliente = cliente;
    }
}
