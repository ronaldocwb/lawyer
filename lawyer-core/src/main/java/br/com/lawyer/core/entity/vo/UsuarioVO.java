package br.com.lawyer.core.entity.vo;

import br.com.lawyer.core.entity.Permissao;
import br.com.lawyer.core.entity.Usuario;
<<<<<<< HEAD
=======
import org.codehaus.jackson.annotate.JsonIgnore;
>>>>>>> origin/master

import java.util.List;

public class UsuarioVO extends BaseVO<Usuario> {

    private String email;

    private String token;

    private List<Permissao> permissoes;

    private String senha;

    public UsuarioVO() {}

    public UsuarioVO(Usuario usuario) {
        super(usuario);
    }

<<<<<<< HEAD
=======
    @JsonIgnore
>>>>>>> origin/master
    public String getSenha () {
        return senha;
    }

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
}
