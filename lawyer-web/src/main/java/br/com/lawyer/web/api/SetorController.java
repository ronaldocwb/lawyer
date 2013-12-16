package br.com.lawyer.web.api;

import br.com.lawyer.core.entity.Setor;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.SetorDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Deividi
 * @since 03/11/2013
 */
@ApiController
public class SetorController {

    @Autowired
    private SetorDelegate setorDelegate;

    @RequestMapping (value = "/setores", method = RequestMethod.GET)
    @ResponseBody
    public Page<Setor> findAll (
            @RequestParam (value = "q", required = false) String query,
            @RequestParam (value = "page", defaultValue = "0", required = false) int page,
            @RequestParam (value = "limit", defaultValue = "25", required = false) int limit) {
        return setorDelegate.findAllByNome(query, page, limit);
    }

    @RequestMapping (value = "/setores", method = RequestMethod.POST)
    @ResponseBody
    public Setor salvar(@RequestBody Setor setorVO) {
        return setorDelegate.salvar(setorVO);
    }
}
