package br.com.lawyer.web.api;

import br.com.lawyer.core.entity.Responsavel;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.ResponsavelDelegate;
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
 * @since 22/10/2013
 */
@ApiController
@Secured ({"ROLE_LAWYER", "ROLE_MANAGER"})
public class ResponsavelController {

    @Autowired
    private ResponsavelDelegate responsavelDelegate;

    @RequestMapping (value = "/responsaveis", method = RequestMethod.GET)
    public @ResponseBody
    Page list(
            @RequestParam (value = "q", required = false) String query,
            @RequestParam (value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "limit", defaultValue = "25", required = false) int limit) throws BusinessException {
        return responsavelDelegate.findResponsavelPorPagina(query, page, limit);
    }

    @RequestMapping(value = "/responsaveis", method = RequestMethod.POST)
    public @ResponseBody
    Responsavel salvarUsuario(@RequestBody Responsavel pessoa) {
        return responsavelDelegate.salvar(pessoa);
    }

    @RequestMapping(value = "/responsaveis/{uid}", method = RequestMethod.DELETE)
    public ResponseEntity excluir(@PathVariable ("uid") String uid) {
        responsavelDelegate.deletar(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/responsaveis/{uid}", method = RequestMethod.PUT)
    public @ResponseBody Responsavel update(@PathVariable("uid") String uid, @RequestBody Responsavel pessoa) throws BusinessException {
        return responsavelDelegate.atualizar(pessoa, uid);
    }

    @RequestMapping(value = "/responsaveis/{uid}", method = RequestMethod.GET)
    public @ResponseBody
    Responsavel findOne(@PathVariable("uid") String uid) {
        return responsavelDelegate.buscarPorUid(uid);
    }

}
