package br.com.lawyer.web.api;

import br.com.lawyer.core.entity.Lembrete;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.LembreteDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Deividi
 * @since 26/09/2013
 */
@ApiController
public class LembreteController {

    @Autowired
    private LembreteDelegate lembreteDelegate;

    @RequestMapping (value = "/lembretes", method = RequestMethod.GET)
    public @ResponseBody
    List<Lembrete> list () throws BusinessException {
        return lembreteDelegate.findAll();
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/lembretes", method = RequestMethod.POST)
    public @ResponseBody
    Lembrete salvar(@RequestBody Lembrete lembrete) throws BusinessException {
        return lembreteDelegate.salvar(lembrete);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/lembretes/{uid}", method = RequestMethod.PUT)
    public @ResponseBody Lembrete update(@PathVariable("uid") String uid, @RequestBody Lembrete lembrete) throws BusinessException {
        return lembreteDelegate.atualizar(lembrete);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/lembretes/all", method = RequestMethod.DELETE)
    public ResponseEntity excluirBatch() throws BusinessException {
        lembreteDelegate.deletarTodos();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
