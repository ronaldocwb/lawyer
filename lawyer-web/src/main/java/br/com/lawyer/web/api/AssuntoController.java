package br.com.lawyer.web.api;

import br.com.lawyer.core.entity.Assunto;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.AssuntoDelegate;
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

import java.util.Date;

/**
 * @author Deividi
 * @since 15/11/2013
 */
@ApiController
public class AssuntoController {

    @Autowired
    private AssuntoDelegate assuntoDelegate;

    @RequestMapping (value = "/assuntos", method = RequestMethod.GET)
    @ResponseBody
    public Page<Assunto> list(@RequestParam (value = "q", required = false) String query,
                                @RequestParam (value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "limit", defaultValue = "25") int limit) {
        return assuntoDelegate.findAssuntoPorNomeOuPagina(query, page, limit);
    }

    @RequestMapping (value = "/assuntos/clientes/{uid}", method = RequestMethod.GET)
    @ResponseBody
    public Page<Assunto> listarPorClienteUid( @PathVariable ("uid") String uid,
                                                @RequestParam (value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "limit", defaultValue = "25") int limit) throws BusinessException {
        return assuntoDelegate.findAssuntosPorClienteUid(uid, page, limit);
    }

    @Secured ({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/assuntos", method = RequestMethod.POST)
    public @ResponseBody Assunto salvar(@RequestBody Assunto assunto) {
        return assuntoDelegate.salvar(assunto);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/assuntos/{uid}", method = RequestMethod.DELETE)
    public ResponseEntity excluir(@PathVariable ("uid") String uid) {
        assuntoDelegate.deletar(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/assuntos/{uid}", method = RequestMethod.PUT)
    public @ResponseBody Assunto update(@PathVariable("uid") String uid, @RequestBody Assunto assunto) throws BusinessException {
        return assuntoDelegate.update(assunto, uid);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/assuntos/{uid}", method = RequestMethod.GET)
    public @ResponseBody Assunto findOne(@PathVariable("uid") String uid) {
        return assuntoDelegate.findOne(uid);
    }

    @RequestMapping(value = "/assuntos/fake", method = RequestMethod.GET)
    public @ResponseBody Assunto salvar() {
        Assunto assunto = new Assunto();
        assunto.setNumeroProcesso(String.valueOf(new Date().getTime()));
        assunto.setNome("Assunto " + assunto.getNumeroProcesso());
        return assuntoDelegate.salvar(assunto);
    }

}
