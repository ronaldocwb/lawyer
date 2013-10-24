package br.com.lawyer.web.api;

import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.ILembreteDelegate;
import br.com.lawyer.web.vo.LembreteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    private ILembreteDelegate lembreteDelegate;

    @RequestMapping (value = "/lembretes", method = RequestMethod.GET)
    public @ResponseBody
    List<LembreteVO> list () throws BusinessException {
        return lembreteDelegate.findAll();
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/lembretes", method = RequestMethod.POST)
    public @ResponseBody
    LembreteVO salvarUsuario (@RequestBody LembreteVO lembrete) {
        return lembreteDelegate.salvar(lembrete);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/lembretes/", method = RequestMethod.DELETE)
    public ResponseEntity excluir(@RequestBody List<LembreteVO> lembretes) {
        lembreteDelegate.deletar(lembretes);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
