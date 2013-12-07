package br.com.lawyer.core.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.UsuarioRepository;

public interface UsuarioService extends BaseService<String, Usuario, UsuarioRepository> {

    Usuario authenticate (Usuario usuario, AuthenticationManager manager);

    Usuario atualizarUsuario (Usuario parse, String uid) throws BusinessException;

    Usuario atualizarSenhaUsuario (Usuario usuario, String uid, String novaSenha) throws AuthenticationException, BusinessException;
    
    Usuario salvar (Usuario usuario) throws BusinessException;

}
