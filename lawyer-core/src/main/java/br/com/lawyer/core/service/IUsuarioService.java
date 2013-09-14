package br.com.lawyer.core.service;


import br.com.lawyer.core.entity.vo.UsuarioVO;
import br.com.lawyer.core.exception.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;

public interface IUsuarioService {
    UsuarioVO authenticate (UsuarioVO authentication, AuthenticationManager manager) throws BusinessException;

    Page<UsuarioVO> findUserByPage (PageRequest page);
}
