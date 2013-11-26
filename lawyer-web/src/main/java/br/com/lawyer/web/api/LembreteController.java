package br.com.lawyer.web.api;

import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.LembreteDelegate;
import br.com.lawyer.web.vo.LembreteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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
    List<LembreteVO> list () throws BusinessException {
        return lembreteDelegate.findAll();
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/lembretes", method = RequestMethod.POST)
    public @ResponseBody
    LembreteVO salvar(@RequestBody LembreteVO lembrete) throws BusinessException {
        return lembreteDelegate.salvar(lembrete);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/lembretes/{uid}", method = RequestMethod.PUT)
    public @ResponseBody LembreteVO update(@PathVariable("uid") String uid, @RequestBody LembreteVO lembrete) throws BusinessException {
        return lembreteDelegate.atualizar(lembrete);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/lembretes/all", method = RequestMethod.DELETE)
    public ResponseEntity excluirBatch() throws BusinessException {
        lembreteDelegate.deletarTodos();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
