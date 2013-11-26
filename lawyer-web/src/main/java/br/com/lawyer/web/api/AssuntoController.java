package br.com.lawyer.web.api;

import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.AssuntoDelegate;
import br.com.lawyer.web.vo.AssuntoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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
    public Page<AssuntoVO> list(@RequestParam (value = "q", required = false) String query,
                                @RequestParam (value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "limit", defaultValue = "25") int limit) {
        return assuntoDelegate.findAssuntoPorNomeOuPagina(query, page, limit);
    }

    @Secured ({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/assuntos", method = RequestMethod.POST)
    public @ResponseBody AssuntoVO salvar(@RequestBody AssuntoVO assuntoVO) {
        return assuntoDelegate.salvar(assuntoVO);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/assuntos/{uid}", method = RequestMethod.DELETE)
    public ResponseEntity excluir(@PathVariable ("uid") String uid) {
        assuntoDelegate.deletar(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/assuntos/{uid}", method = RequestMethod.PUT)
    public @ResponseBody AssuntoVO update(@PathVariable("uid") String uid, @RequestBody AssuntoVO assuntoVO) throws BusinessException {
        return assuntoDelegate.update(assuntoVO, uid);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/assuntos/{uid}", method = RequestMethod.GET)
    public @ResponseBody AssuntoVO findOne(@PathVariable("uid") String uid) {
        return assuntoDelegate.findOne(uid);
    }

    @RequestMapping(value = "/assuntos/fake", method = RequestMethod.GET)
    public @ResponseBody AssuntoVO salvar() {
        AssuntoVO assuntoVO = new AssuntoVO();
        assuntoVO.setNumeroProcesso(String.valueOf(new Date().getTime()));
        assuntoVO.setNome("Assunto " + assuntoVO.getNumeroProcesso());
        return assuntoDelegate.salvar(assuntoVO);
    }

}
