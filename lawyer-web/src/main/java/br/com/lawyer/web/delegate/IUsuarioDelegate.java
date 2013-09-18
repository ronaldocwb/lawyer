package br.com.lawyer.web.delegate;


import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.vo.UsuarioVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;

public interface IUsuarioDelegate {
    UsuarioVO authenticate (UsuarioVO authentication, AuthenticationManager manager) throws BusinessException;

    Page<UsuarioVO> findUserByPage (PageRequest page);
}
