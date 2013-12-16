package br.com.lawyer.web.api;

import br.com.lawyer.core.entity.Advogado;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.AdvogadoDelegate;
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
 * @author Ronaldo
 * @since 23/10/2013
 */
@ApiController
@Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
public class AdvogadoController {

    @Autowired
    private AdvogadoDelegate advogadoDelegate;

    @RequestMapping (value = "/advogados", method = RequestMethod.GET)
    public @ResponseBody Page<Advogado> list(
                @RequestParam (value = "q", required = false) String q,
                @RequestParam (value = "page", defaultValue = "0", required = false) int page,
                @RequestParam(value = "limit", defaultValue = "25", required = false) int limit) {
        return advogadoDelegate.findAdvogadoPorPagina(q, page, limit);
    }

    @RequestMapping(value = "/advogados", method = RequestMethod.POST)
    public @ResponseBody Advogado salvarUsuario(@RequestBody Advogado advogadoVO) throws BusinessException {
        return advogadoDelegate.salvar(advogadoVO);
    }

    @RequestMapping(value = "/advogados/{uid}", method = RequestMethod.DELETE)
    public ResponseEntity excluir(@PathVariable ("uid") String uid) {
        advogadoDelegate.deletar(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/advogados/{uid}", method = RequestMethod.PUT)
    public @ResponseBody Advogado update(@PathVariable("uid") String uid, @RequestBody Advogado advogadoVO) throws BusinessException {
        return advogadoDelegate.atualizar(advogadoVO, uid);
    }

    @RequestMapping(value = "/advogados/{uid}", method = RequestMethod.GET)
    public @ResponseBody Advogado findOne(@PathVariable("uid") String uid) {
        return advogadoDelegate.buscarPorUid(uid);
    }

    @RequestMapping(value = "/advogados/pessoas/{uid}", method = RequestMethod.GET)
    public @ResponseBody Advogado findAdvogadoPorPessoaUid(@PathVariable("uid") String pessoaUid) {
        return advogadoDelegate.buscarAdvogadoPorPessoaUid(pessoaUid);
    }

}
