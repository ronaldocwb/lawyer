package br.com.lawyer.web.api;

import br.com.lawyer.core.authentication.LawyerAuthenticationToken;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.UsuarioDelegate;
import br.com.lawyer.web.vo.UsuarioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Classe para gerenciamento do endpoint Usuario na API.
 * @author Deividi Cavarzan
 *
 */
@ApiController
public class UsuarioController {

    @Autowired
    private UsuarioDelegate usuarioDelegate;

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public @ResponseBody Page list(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "25") int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        return usuarioDelegate.findUserByPage(pageRequest);
    }

    @Secured ({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/usuarios", method = RequestMethod.POST)
    public @ResponseBody UsuarioVO salvarUsuario(@RequestBody UsuarioVO usuarioVO) {
        return usuarioDelegate.salvar(usuarioVO);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/usuarios/{uid}", method = RequestMethod.DELETE)
    public ResponseEntity excluir(@PathVariable("uid") String uid) {
        usuarioDelegate.deletar(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/usuarios/{uid}", method = RequestMethod.PUT)
    public @ResponseBody UsuarioVO update(@PathVariable("uid") String uid, @RequestBody UsuarioVO usuarioVO) throws BusinessException {
        return usuarioDelegate.update(usuarioVO, uid);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/usuarios/{token}/senha", method = RequestMethod.PUT)
    public @ResponseBody UsuarioVO updateSenha(@PathVariable("token") String token, @RequestBody UsuarioVO usuarioVO) throws BusinessException {
        String novaSenha = usuarioVO.getNovaSenha();
        return usuarioDelegate.updateSenha(usuarioVO, token, novaSenha);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/usuarios/{uid}", method = RequestMethod.GET)
    public @ResponseBody UsuarioVO findOne(@PathVariable("uid") String uid) {
        return usuarioDelegate.findOne(uid);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/usuarios/atual", method = RequestMethod.GET)
    public @ResponseBody UsuarioVO findCurrent() throws BusinessException {
        UsuarioVO usuario = usuarioDelegate.getUsuarioAtual();
        LawyerAuthenticationToken token = (LawyerAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        usuario.setToken(token.getToken());
        return usuario;
    }

}
