package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.Advocacia;
import br.com.lawyer.core.entity.Advogado;
import br.com.lawyer.core.entity.Pessoa;
import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.entity.enumerated.Permissao;
import br.com.lawyer.core.entity.enumerated.StatusUsuario;
import br.com.lawyer.core.entity.enumerated.TipoUsuario;
import br.com.lawyer.web.annotation.IgnoreVOParser;
import br.com.lawyer.web.base.BaseVO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UsuarioVO extends BaseVO<Usuario> {

	private static final long serialVersionUID = 1L;

	private String uid;
	
	private String email;

    @IgnoreVOParser
    private String token;

    private List<Permissao> permissoes;

    private String senha;

    @IgnoreVOParser
    private boolean criarContato;

    @IgnoreVOParser
    private Advogado advogado;

    @IgnoreVOParser
    private String novaSenha;

    private TipoUsuario tipoUsuario = TipoUsuario.USUARIO;

    @JsonBackReference
    private Pessoa pessoa;

    private StatusUsuario ativo;

    private Advocacia advocacia;

    public UsuarioVO() {}

    public UsuarioVO(Usuario usuario) {
        super(usuario);
    }
    
    public UsuarioVO(String email) {
		super();
		this.email = email;
	}
    
    public String getUid () {
        return uid;
    }

    public void setUid (String uid) {
        this.uid = uid;
    }

	@JsonIgnore
    public String getSenha () {
        return senha;
    }

    @JsonProperty
    public void setSenha (String senha) {
        this.senha = senha;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getToken () {
        return token;
    }

    public void setToken (String token) {
        this.token = token;
    }

    public List<Permissao> getPermissoes () {
        return permissoes;
    }

    public void setPermissoes (List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    @JsonIgnore
    public String getNovaSenha () {
        return novaSenha;
    }

    @JsonProperty
    public void setNovaSenha (String novaSenha) {
        this.novaSenha = novaSenha;
    }

    public Advocacia getAdvocacia () {
        return advocacia;
    }

    public void setAdvocacia (Advocacia advocacia) {
        this.advocacia = advocacia;
    }

    public static long getSerialVersionUID () {
        return serialVersionUID;
    }

    public TipoUsuario getTipoUsuario () {
        return tipoUsuario;
    }

    public void setTipoUsuario (TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Pessoa getPessoa () {
        return pessoa;
    }

    public void setPessoa (Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public StatusUsuario getAtivo () {
        return ativo;
    }

    public void setAtivo (StatusUsuario ativo) {
        this.ativo = ativo;
    }

    @JsonIgnore
    public Advogado getAdvogado () {
        return advogado;
    }

    @JsonProperty
    public void setAdvogado (Advogado advogado) {
        this.advogado = advogado;
    }

    public boolean isCriarContato () {
        return criarContato;
    }

    public void setCriarContato (boolean criarContato) {
        this.criarContato = criarContato;
    }
}
