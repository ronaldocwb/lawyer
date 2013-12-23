package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;
import br.com.lawyer.core.entity.enumerated.Permissao;
import br.com.lawyer.core.entity.enumerated.StatusUsuario;
import br.com.lawyer.core.entity.enumerated.TipoUsuario;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import java.util.List;

@Entity
public class Usuario extends AbstractBaseEntity {

    @Column (length = 200)
    private String email;

    @Column (length = 128)
    private String senha;

    @ManyToOne
    private Advocacia advocacia;

    @Enumerated
    private TipoUsuario tipoUsuario;

    @OneToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    private Pessoa pessoa;
    
    @Enumerated(EnumType.ORDINAL)
    private StatusUsuario ativo;

	@ElementCollection (fetch = FetchType.EAGER, targetClass = Permissao.class)
    @Enumerated (value = EnumType.STRING)
    @JoinColumn (columnDefinition = "Permissao")
    private List<Permissao> permissoes;

    public Usuario () {}

    public Usuario (String value) {
        super();
        this.email = value;
    }

    @PrePersist
	public void prePersist(){
		if(getAtivo() == null){
			setAtivo(StatusUsuario.SEM_ATIVACAO_INICIAL);
		}
	}

    public String getSenha () {
        return senha;
    }

    public void setSenha (String senhaCriptografada) {
        this.senha = senhaCriptografada;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public List<Permissao> getPermissoes () {
        return permissoes;
    }

    public void setPermissoes (List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    public Advocacia getAdvocacia () {
        return advocacia;
    }

    public void setAdvocacia (Advocacia advocacia) {
        this.advocacia = advocacia;
    }

    public Pessoa getPessoa () {
        return pessoa;
    }

    public void setPessoa (Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public StatusUsuario getAtivo() {
		return ativo;
	}

	public void setAtivo(StatusUsuario ativo) {
		this.ativo = ativo;
	}

    public TipoUsuario getTipoUsuario () {
        return tipoUsuario;
    }

    public void setTipoUsuario (TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
