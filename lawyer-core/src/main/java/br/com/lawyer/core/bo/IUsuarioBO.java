package br.com.lawyer.core.bo;

import br.com.lawyer.core.base.IBaseBO;
import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.IUsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;

public interface IUsuarioBO extends IBaseBO<String, Usuario, IUsuarioRepository> {

    Usuario authenticate (Usuario usuario, AuthenticationManager manager) throws BusinessException;
}
