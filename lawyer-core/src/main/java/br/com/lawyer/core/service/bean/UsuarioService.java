package br.com.lawyer.core.service.bean;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.bo.IUsuarioBO;
import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.entity.vo.UsuarioVO;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends BaseService<Usuario, UsuarioVO> implements IUsuarioService {

    @Autowired
    private IUsuarioBO usuarioBO;

    @Override
    public UsuarioVO authenticate (UsuarioVO usuarioVO, AuthenticationManager manager) throws BusinessException {

        Usuario usuario = usuarioBO.authenticate(usuarioVO.parse(), manager);

        return getVO(usuario);
    }

    @Override
    public Page<UsuarioVO> findUserByPage (PageRequest page) {
        Page<Usuario> result = usuarioBO.findAll(page);
        return getVO(result, page);
    }

}
