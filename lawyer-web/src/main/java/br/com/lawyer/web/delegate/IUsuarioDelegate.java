package br.com.lawyer.web.delegate;


import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.vo.UsuarioVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;

public interface IUsuarioDelegate {
    UsuarioVO authenticate (UsuarioVO authentication, AuthenticationManager manager) throws BusinessException;

    Page<UsuarioVO> findUserByPage (PageRequest page);

    UsuarioVO salvar (UsuarioVO usuarioVO);

    void deletar (String uid);

    UsuarioVO update (UsuarioVO usuarioVO, String uid) throws BusinessException;

    UsuarioVO findOne (String uid);

    UsuarioVO getUsuarioAtual () throws BusinessException;


    UsuarioVO updateSenha (UsuarioVO usuarioVO, String uid, String novaSenha) throws BusinessException;
}
