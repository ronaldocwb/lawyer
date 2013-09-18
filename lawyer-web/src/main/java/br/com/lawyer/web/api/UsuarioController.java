package br.com.lawyer.web.api;

import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.IUsuarioDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Classe para gerenciamento do endpoint Usuario na API.
 * @author Deividi Cavarzan
 *
 */
@ApiController
public class UsuarioController {

    @Autowired
    private IUsuarioDelegate usuarioDelegate;

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public @ResponseBody Page list(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "25") int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        return usuarioDelegate.findUserByPage(pageRequest);
    }

}
