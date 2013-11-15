package br.com.lawyer.web.api;

import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.IAtividadeDelegate;
import br.com.lawyer.web.vo.AtividadeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

/**
 * @author Deividi
 * @since 15/11/2013
 */
@ApiController
public class AtividadeController {

    @Autowired
    private IAtividadeDelegate atividadeDelegate;

    @RequestMapping (value = "/atividades", method = RequestMethod.GET)
    @ResponseBody
    public Page<AtividadeVO> findAtividades(@RequestParam (value = "q", required = false) String q,
                                            @RequestParam (value = "page", defaultValue = "0", required = false) int page,
                                            @RequestParam(value = "limit", defaultValue = "25", required = false) int limit) {
    return atividadeDelegate.findAtividadePorPagina(q, page, limit);
    }

    @RequestMapping(value = "/atividades", method = RequestMethod.POST)
    @ResponseBody
    public AtividadeVO cadastrarAtividade (@RequestBody AtividadeVO atividadeVO) {
        return atividadeDelegate.salvar(atividadeVO);
    }

    @Secured ({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/atividades/{uid}", method = RequestMethod.DELETE)
    public ResponseEntity excluir(@PathVariable ("uid") String uid) {
        atividadeDelegate.deletar(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/atividades/{uid}", method = RequestMethod.PUT)
    @ResponseBody
    public AtividadeVO update(@PathVariable("uid") String uid, @RequestBody AtividadeVO AtividadeVO) throws BusinessException {
        return atividadeDelegate.atualizar(AtividadeVO, uid);
    }

    @RequestMapping(value = "/atividades/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public AtividadeVO findOne(@PathVariable("uid") String uid) {
        return atividadeDelegate.buscarPorUid(uid);
    }
}
