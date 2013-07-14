package br.com.lawyer.core.authentication;

import br.com.lawyer.core.entity.Usuario;

public interface AuthenticationService {

    Usuario getUsuarioParaAutenticacao(String email);

}
