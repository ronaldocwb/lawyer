package br.com.lawyer.web.api;

import br.com.lawyer.core.service.IUsuarioService;
import br.com.lawyer.web.annotation.ApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@ApiController
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public @ResponseBody Page list() {
        PageRequest page = new PageRequest(0,10);
        return usuarioService.findUserByPage(page);
    }

}
