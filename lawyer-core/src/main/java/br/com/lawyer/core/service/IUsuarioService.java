package br.com.lawyer.core.service;

import br.com.lawyer.core.base.IBaseService;
import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.IUsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;

public interface IUsuarioService extends IBaseService<String, Usuario, IUsuarioRepository> {

    Usuario authenticate (Usuario usuario, AuthenticationManager manager);

    Usuario atualizarUsuario (Usuario parse, String uid) throws BusinessException;
}
