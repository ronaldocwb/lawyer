package br.com.lawyer.web.api;

import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.IAdvogadoDelegate;
import br.com.lawyer.web.vo.AdvogadoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ronaldo
 * @since 23/10/2013
 */
@ApiController
public class AdvogadoController {

    @Autowired
    private IAdvogadoDelegate advogadoDelegate;

    @RequestMapping (value = "/advogados", method = RequestMethod.GET)
    public @ResponseBody Page list(
                @RequestParam (value = "q", required = false) String q,
                @RequestParam (value = "page", defaultValue = "0", required = false) int page,
                @RequestParam(value = "limit", defaultValue = "25", required = false) int limit) {
        return advogadoDelegate.findAdvogadoPorPagina(q, page, limit);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/advogados", method = RequestMethod.POST)
    public @ResponseBody AdvogadoVO salvarUsuario(@RequestBody AdvogadoVO advogadoVO) {
        return advogadoDelegate.salvar(advogadoVO);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/advogados/{uid}", method = RequestMethod.DELETE)
    public ResponseEntity excluir(@PathVariable ("uid") String uid) {
        advogadoDelegate.deletar(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/advogados/{uid}", method = RequestMethod.PUT)
    public @ResponseBody AdvogadoVO update(@PathVariable("uid") String uid, @RequestBody AdvogadoVO advogadoVO) throws BusinessException {
        return advogadoDelegate.atualizar(advogadoVO, uid);
    }

    @RequestMapping(value = "/advogados/{uid}", method = RequestMethod.GET)
    public @ResponseBody AdvogadoVO findOne(@PathVariable("uid") String uid) {
        return advogadoDelegate.buscarPorUid(uid);
    }

}
