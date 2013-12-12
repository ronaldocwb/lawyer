package br.com.lawyer.web.api;

import br.com.lawyer.core.entity.Atividade;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.AtividadeDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Deividi
 * @since 15/11/2013
 */
@ApiController
public class AtividadeController {

    @Autowired
    private AtividadeDelegate atividadeDelegate;

    @RequestMapping (value = "/atividades", method = RequestMethod.GET)
    @ResponseBody
    public Page<Atividade> findAtividades(@RequestParam (value = "q", required = false) String q,
                                            @RequestParam (value = "page", defaultValue = "0", required = false) int page,
                                            @RequestParam(value = "limit", defaultValue = "25", required = false) int limit) {
    return atividadeDelegate.findAtividadePorPagina(q, page, limit);
    }

    @RequestMapping(value = "/atividades", method = RequestMethod.POST)
    @ResponseBody
    public Atividade cadastrarAtividade (@RequestBody Atividade atividade) {
        return atividadeDelegate.salvar(atividade);
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
    public Atividade update(@PathVariable("uid") String uid, @RequestBody Atividade Atividade) throws BusinessException {
        return atividadeDelegate.atualizar(Atividade, uid);
    }

    @RequestMapping(value = "/atividades/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public Atividade findOne(@PathVariable("uid") String uid) {
        return atividadeDelegate.buscarPorUid(uid);
    }
}
