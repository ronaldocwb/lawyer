package br.com.lawyer.core.service;

import br.com.lawyer.core.base.IBaseService;
import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.IUsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;

public interface IUsuarioService extends IBaseService<String, Usuario, IUsuarioRepository> {

    Usuario authenticate (Usuario usuario, AuthenticationManager manager);

    Usuario atualizarUsuario (Usuario parse, String uid) throws BusinessException;

    Usuario atualizarSenhaUsuario (Usuario usuario, String uid, String novaSenha) throws AuthenticationException, BusinessException;

}
