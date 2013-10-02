package br.com.lawyer.web.api;

import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.IEmpresaDelegate;
import br.com.lawyer.web.vo.EmpresaVO;
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
public class EmpresaController {

    @Autowired
    private IEmpresaDelegate empresaDelegate;

    @RequestMapping (value = "/empresas", method = RequestMethod.GET)
    public @ResponseBody Page list(
                @RequestParam (value = "q", required = false) String q,
                @RequestParam (value = "page", defaultValue = "0", required = false) int page,
                @RequestParam(value = "limit", defaultValue = "25", required = false) int limit) {
        return empresaDelegate.findEmpresaPorPagina(q, page, limit);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/empresas", method = RequestMethod.POST)
    public @ResponseBody EmpresaVO salvarUsuario(@RequestBody EmpresaVO empresaVO) {
        return empresaDelegate.salvar(empresaVO);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/empresas/{uid}", method = RequestMethod.DELETE)
    public ResponseEntity excluir(@PathVariable ("uid") String uid) {
        empresaDelegate.deletar(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/empresas/{uid}", method = RequestMethod.PUT)
    public @ResponseBody EmpresaVO update(@PathVariable("uid") String uid, @RequestBody EmpresaVO EmpresaVO) throws BusinessException {
        return empresaDelegate.atualizar(EmpresaVO, uid);
    }

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    public @ResponseBody EmpresaVO findOne(@PathVariable("uid") String uid) {
        return empresaDelegate.buscarPorUid(uid);
    }

}
