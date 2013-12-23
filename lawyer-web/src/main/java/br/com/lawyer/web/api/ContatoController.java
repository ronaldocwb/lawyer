package br.com.lawyer.web.api;

import br.com.lawyer.core.entity.Contato;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.ContatoDelegate;
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
 * @since 31/10/2013
 */
@ApiController
public class ContatoController {

    @Autowired
    private ContatoDelegate contatoDelegate;

    @RequestMapping (value = "/contatos", method = RequestMethod.GET)
    @ResponseBody
    public Page<Contato> findContatos(@RequestParam (value = "q", required = false) String q,
                                         @RequestParam (value = "tipo", required = false) String tipo,
                                           @RequestParam (value = "page", defaultValue = "0", required = false) int page,
                                           @RequestParam (value = "limit", defaultValue = "25", required = false) int limit) {
        return contatoDelegate.findContatos(q, tipo, page, limit);
    }

    @RequestMapping (value = "/contatos/empresas", method = RequestMethod.GET)
    @ResponseBody
    public Page<Contato> findContatosEmpresas(@RequestParam (value = "q", required = false) String q,
                                           @RequestParam (value = "page", defaultValue = "0", required = false) int page,
                                           @RequestParam (value = "limit", defaultValue = "25", required = false) int limit) {
        return contatoDelegate.findContatosEmpresas(q, page, limit);
    }

    @RequestMapping (value = "/contatos/pessoas", method = RequestMethod.GET)
    @ResponseBody
    public Page<Contato> findContatosPessoas(@RequestParam (value = "q", required = false) String q,
                                           @RequestParam (value = "page", defaultValue = "0", required = false) int page,
                                           @RequestParam (value = "limit", defaultValue = "25", required = false) int limit) {
        return contatoDelegate.findContatosPessoas(q, page, limit);
    }

    @RequestMapping (value = "/contatos/usuarios", method = RequestMethod.GET)
    @ResponseBody
    public Page<Contato> findContatosUsuarios(@RequestParam (value = "q", required = false) String q,
                                           @RequestParam (value = "page", defaultValue = "0", required = false) int page,
                                           @RequestParam (value = "limit", defaultValue = "25", required = false) int limit) {
        return contatoDelegate.findContatosUsuarios(q, page, limit);
    }

    @Secured ({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/contatos", method = RequestMethod.POST)
    public @ResponseBody Contato salvar(@RequestBody Contato contato) throws BusinessException {
        return contatoDelegate.salvar(contato);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/contatos/{uid}", method = RequestMethod.DELETE)
    public ResponseEntity excluir(@PathVariable ("uid") String uid) throws BusinessException {
        contatoDelegate.deletar(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/contatos/{uid}", method = RequestMethod.PUT)
    public @ResponseBody Contato update(@PathVariable("uid") String uid, @RequestBody Contato contato) throws BusinessException {
        return contatoDelegate.atualizar(contato, uid);
    }

    @RequestMapping(value = "/contatos/{uid}", method = RequestMethod.GET)
    public @ResponseBody Contato findOne(@PathVariable("uid") String uid) {
        return contatoDelegate.buscarPorUid(uid);
    }

}
