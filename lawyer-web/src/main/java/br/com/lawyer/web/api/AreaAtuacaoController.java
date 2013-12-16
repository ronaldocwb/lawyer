package br.com.lawyer.web.api;

import br.com.lawyer.core.entity.AreaAtuacao;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.AreaAtuacaoDelegate;
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
 * @since 26/09/2013
 */
@ApiController
public class AreaAtuacaoController {

    @Autowired
    private AreaAtuacaoDelegate areaAtuacaoDelegate;

    @RequestMapping (value = "/areasAtuacao", method = RequestMethod.GET)
    public @ResponseBody Page list(
                @RequestParam (value = "q", required = false) String q,
                @RequestParam (value = "page", defaultValue = "0", required = false) int page,
                @RequestParam(value = "limit", defaultValue = "25", required = false) int limit) {
        return areaAtuacaoDelegate.findAreaAtuacaoPorPagina(q, page, limit);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/areasAtuacao", method = RequestMethod.POST)
    public @ResponseBody
    AreaAtuacao salvarUsuario(@RequestBody AreaAtuacao areaAtuacao) {
        return areaAtuacaoDelegate.salvar(areaAtuacao);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/areasAtuacao/{uid}", method = RequestMethod.DELETE)
    public ResponseEntity excluir(@PathVariable ("uid") String uid) {
        areaAtuacaoDelegate.deletar(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/areasAtuacao/{uid}", method = RequestMethod.PUT)
    public @ResponseBody AreaAtuacao update(@PathVariable("uid") String uid, @RequestBody AreaAtuacao areaAtuacao) throws BusinessException {
        return areaAtuacaoDelegate.atualizar(areaAtuacao, uid);
    }

    @RequestMapping(value = "/areasAtuacao/{uid}", method = RequestMethod.GET)
    public @ResponseBody AreaAtuacao findOne(@PathVariable("uid") String uid) {
        return areaAtuacaoDelegate.buscarPorUid(uid);
    }

}
