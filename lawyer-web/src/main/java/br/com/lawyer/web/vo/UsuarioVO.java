package br.com.lawyer.web.vo;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.entity.enumerated.Permissao;
import br.com.lawyer.web.annotation.IgnoreVOParser;
import br.com.lawyer.web.base.BaseVO;

public class UsuarioVO extends BaseVO<Usuario> {

	private static final long serialVersionUID = 1L;

	private String uid;
	
	private String email;

    @IgnoreVOParser
    private String token;

    private List<Permissao> permissoes;

    private String senha;

    @IgnoreVOParser
    private String novaSenha;

    private AdvocaciaVO advocacia;

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

    public AdvocaciaVO getAdvocacia () {
        return advocacia;
    }

    public void setAdvocacia (AdvocaciaVO advocacia) {
        this.advocacia = advocacia;
    }
}
