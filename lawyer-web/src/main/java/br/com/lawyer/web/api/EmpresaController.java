package br.com.lawyer.web.api;

import br.com.lawyer.core.entity.Empresa;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.EmpresaDelegate;
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
 * @since 26/09/2013
 */
@ApiController
public class EmpresaController {

    @Autowired
    private EmpresaDelegate empresaDelegate;

    @RequestMapping (value = "/empresas", method = RequestMethod.GET)
    public @ResponseBody Page list(
                @RequestParam (value = "q", required = false) String q,
                @RequestParam (value = "page", defaultValue = "0", required = false) int page,
                @RequestParam(value = "limit", defaultValue = "25", required = false) int limit) {
        return empresaDelegate.findEmpresaPorPagina(q, page, limit);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/empresas", method = RequestMethod.POST)
    public @ResponseBody
    Empresa salvarUsuario(@RequestBody Empresa empresa) throws BusinessException {
        return empresaDelegate.salvar(empresa);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/empresas/{uid}", method = RequestMethod.DELETE)
    public ResponseEntity excluir(@PathVariable ("uid") String uid) throws BusinessException {
        empresaDelegate.deletar(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({"ROLE_LAWYER", "ROLE_MANAGER"})
    @RequestMapping(value = "/empresas/{uid}", method = RequestMethod.PUT)
    public @ResponseBody Empresa update(@PathVariable("uid") String uid, @RequestBody Empresa Empresa) throws BusinessException {
        return empresaDelegate.atualizar(Empresa, uid);
    }

    @RequestMapping(value = "/empresas/{uid}", method = RequestMethod.GET)
    public @ResponseBody Empresa findOne(@PathVariable("uid") String uid) {
        return empresaDelegate.buscarPorUid(uid);
    }

}
