package br.com.lawyer.web.api;

import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.IUsuarioDelegate;
import br.com.lawyer.web.vo.UsuarioVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * Classe para gerenciamento do endpoint Usuario na API.
 * @author Deividi Cavarzan
 *
 */
@ApiController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioDelegate usuarioDelegate;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Page list(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "25") int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        return usuarioDelegate.findUserByPage(pageRequest);
    }

    @Secured(value = {"MANAGER", "LAWYER"})
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody UsuarioVO salvarUsuario(UsuarioVO usuarioVO) {
        return usuarioDelegate.salvar(usuarioVO);
    }

    @Secured(value = {"MANAGER", "LAWYER"})
    @RequestMapping(value = "/{uid}", method = RequestMethod.DELETE)
    public ResponseEntity excluir(@PathVariable("uid") String uid) {
        usuarioDelegate.deletar(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured(value = {"MANAGER", "LAWYER"})
    @RequestMapping(value = "/{uid}", method = RequestMethod.PUT)
    public @ResponseBody UsuarioVO update(@PathVariable("uid") String uid, @RequestBody UsuarioVO usuarioVO) throws BusinessException {
        return usuarioDelegate.update(usuarioVO, uid);
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    public @ResponseBody UsuarioVO findOne(@PathVariable("uid") String uid) {
        return usuarioDelegate.findOne(uid);
    }

}
