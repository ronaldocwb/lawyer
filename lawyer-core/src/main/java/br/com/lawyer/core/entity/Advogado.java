package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Advogado extends AbstractBaseEntity {

    @OneToOne (fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinColumn(unique = true, nullable = false)
    private Pessoa pessoa;

    @Column (length = 20)
    private String numeroOAB;

    public Advogado () {
    }

    public Advogado (Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public boolean hasUsuario(){
    	return getPessoa().getUsuario() != null;
    }

    public Pessoa getPessoa () {
        return pessoa;
    }

    public String getNumeroOAB () {
        return numeroOAB;
    }

    public void setNumeroOAB (String numeroOAB) {
        this.numeroOAB = numeroOAB;
    }

    // precisa pra fazer o parse do VO
    public void setPessoa (Pessoa pessoa) {
        this.pessoa = pessoa;
    }

	public String getNome() {
		return getPessoa().getNome();
	}

	public String getEmailLogin() {
		return getPessoa().getUsuario().getEmail();
	}
}
