package br.com.lawyer.web.api;

import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.AreaAtuacaoDelegate;
import br.com.lawyer.web.vo.AreaAtuacaoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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
    public @ResponseBody AreaAtuacaoVO salvarUsuario(@RequestBody AreaAtuacaoVO areaAtuacaoVO) {
        return areaAtuacaoDelegate.salvar(areaAtuacaoVO);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/areasAtuacao/{uid}", method = RequestMethod.DELETE)
    public ResponseEntity excluir(@PathVariable ("uid") String uid) {
        areaAtuacaoDelegate.deletar(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/areasAtuacao/{uid}", method = RequestMethod.PUT)
    public @ResponseBody AreaAtuacaoVO update(@PathVariable("uid") String uid, @RequestBody AreaAtuacaoVO AreaAtuacaoVO) throws BusinessException {
        return areaAtuacaoDelegate.atualizar(AreaAtuacaoVO, uid);
    }

    @RequestMapping(value = "/areasAtuacao/{uid}", method = RequestMethod.GET)
    public @ResponseBody AreaAtuacaoVO findOne(@PathVariable("uid") String uid) {
        return areaAtuacaoDelegate.buscarPorUid(uid);
    }

}
