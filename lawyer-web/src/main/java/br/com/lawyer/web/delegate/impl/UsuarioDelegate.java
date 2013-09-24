package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.service.IUsuarioService;
import br.com.lawyer.web.base.BaseDelegate;
import br.com.lawyer.web.delegate.IUsuarioDelegate;
import br.com.lawyer.web.vo.UsuarioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDelegate extends BaseDelegate<Usuario, UsuarioVO> implements IUsuarioDelegate {

    @Autowired
    private IUsuarioService usuarioService;

    /**
     * Realiza a autenticacao do usuario retornando um UsuarioVO com os dados do usuario.
     * @param usuarioVO
     * @param manager
     * @return
     * @throws BusinessException
     */
    @Override
    public UsuarioVO authenticate (UsuarioVO usuarioVO, AuthenticationManager manager) throws BusinessException {

        Usuario usuario = usuarioService.authenticate(usuarioVO.parse(), manager);

        return getVO(usuario);
    }

    /**
     * Lista todos os usuarios que existem na página informada.
     * @param page
     * @return Page com a lista de resultados do UsuarioVO
     */
    @Override
    public Page<UsuarioVO> findUserByPage (PageRequest page) {
        Page<Usuario> result = usuarioService.findAll(page);
        return getVO(result, page);
    }

    @Override
    public UsuarioVO salvar (UsuarioVO usuarioVO) {
        Usuario usuario = usuarioService.saveAndFlush(usuarioVO.parse());
        return getVO(usuario);
    }

    @Override
    public void deletar (String uid) {
        usuarioService.delete(uid);
    }

    @Override
    public UsuarioVO update (UsuarioVO usuarioVO, String uid) throws BusinessException {
        Usuario usuario = usuarioService.atualizarUsuario(usuarioVO.parse(), uid);
        return getVO(usuario);
    }

    @Override
    public UsuarioVO findOne (String uid) {
        Usuario usuario = usuarioService.findOne(uid);
        return getVO(usuario);
    }

}
