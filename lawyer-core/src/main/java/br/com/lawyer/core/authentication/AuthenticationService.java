package br.com.lawyer.core.authentication;

import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.exception.BusinessException;

public interface AuthenticationService {

    Usuario getUsuarioParaAutenticacao(String email) throws BusinessException;

}
