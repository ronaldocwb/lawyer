package br.com.lawyer.web.api;

import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.IPessoaDelegate;
import br.com.lawyer.web.vo.PessoaVO;
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
public class PessoaController {

    @Autowired
    private IPessoaDelegate pessoaDelegate;

    @RequestMapping (value = "/pessoas", method = RequestMethod.GET)
    public @ResponseBody Page list(
                @RequestParam (value = "q", required = false) String q,
                @RequestParam (value = "page", defaultValue = "0", required = false) int page,
                @RequestParam(value = "limit", defaultValue = "25", required = false) int limit) {
        return pessoaDelegate.findPessoaPorPagina(q, page, limit);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/pessoas", method = RequestMethod.POST)
    public @ResponseBody PessoaVO salvarUsuario(@RequestBody PessoaVO pessoaVO) {
        return pessoaDelegate.salvar(pessoaVO);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/pessoas/{uid}", method = RequestMethod.DELETE)
    public ResponseEntity excluir(@PathVariable ("uid") String uid) {
        pessoaDelegate.deletar(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/pessoas/{uid}", method = RequestMethod.PUT)
    public @ResponseBody PessoaVO update(@PathVariable("uid") String uid, @RequestBody PessoaVO pessoaVO) throws BusinessException {
        return pessoaDelegate.atualizar(pessoaVO, uid);
    }

    @RequestMapping(value = "/pessoas/{uid}", method = RequestMethod.GET)
    public @ResponseBody PessoaVO findOne(@PathVariable("uid") String uid) {
        return pessoaDelegate.buscarPorUid(uid);
    }

}
