package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.service.UsuarioService;
import br.com.lawyer.web.base.BaseDelegate;
import br.com.lawyer.web.delegate.UsuarioDelegate;
import br.com.lawyer.web.vo.UsuarioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioDelegateImpl extends BaseDelegate<Usuario, UsuarioVO> implements UsuarioDelegate {

    @Autowired
    private UsuarioService usuarioService;

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
     * Lista todos os usuarios que existem na pagina informada.
     * @param page
     * @return Page com a lista de resultados do UsuarioVO
     */
    @Override
    public Page<UsuarioVO> findUserByPage (PageRequest page) {
        Page<Usuario> result = usuarioService.findAll(page);
        return getVO(result, page);
    }

    @Transactional
    @Override
    public UsuarioVO salvar (UsuarioVO usuarioVO) throws BusinessException {
        Usuario usuario = usuarioService.salvarUsuario(usuarioVO.parse(), usuarioVO.isCriarContato(), usuarioVO.getAdvogado());
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

    @Override
    public UsuarioVO getUsuarioAtual () throws BusinessException {
        Usuario usuario = usuarioService.getUsuarioLogado();
        return getVO(usuario);
    }

    @Override
    public UsuarioVO updateSenha (UsuarioVO usuarioVO, String token, String novaSenha) throws BusinessException {
        Usuario usuario = usuarioService.atualizarSenhaUsuario(usuarioVO.parse(), token, novaSenha);
        return getVO(usuario);
    }

}
