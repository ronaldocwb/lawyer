package br.com.lawyer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Deividi Cavarzan
 */
@Controller
public class AuthenticationController {

    /**
     * Esse é o mapeamento que o Spring Security redireciona quando o usuário acessa um contexto protegido por ele.
     * Foi criado esse mapeamento para a URL fica amigável, /auth, ao invés de /auth/login.html
     * @return {String} pagina de login
     */
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String auth() {
        return "auth/login";
    }

}