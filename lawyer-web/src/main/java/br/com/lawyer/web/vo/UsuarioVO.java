package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.entity.enumerated.Permissao;
import br.com.lawyer.web.annotation.IgnoreVOParser;
import br.com.lawyer.web.base.BaseVO;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class UsuarioVO extends BaseVO<Usuario> {

    private String email;

    @IgnoreVOParser
    private String token;

    private List<Permissao> permissoes;

    private String senha;

    @IgnoreVOParser
    private String novaSenha;

    private ClienteVO advocacia;

    public UsuarioVO() {}

    public UsuarioVO(Usuario usuario) {
        super(usuario);
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

    public ClienteVO getAdvocacia () {
        return advocacia;
    }

    public void setAdvocacia (ClienteVO advocacia) {
        this.advocacia = advocacia;
    }
}
