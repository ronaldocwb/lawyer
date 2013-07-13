package br.com.lawyer.web.controller;

import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import sun.org.mozilla.javascript.internal.Function;
import sun.util.resources.LocaleNames_lt;

/**
 * @author Deividi Cavarzan
 * Classe que processa os contextos iniciais da aplicação.
 */
@Controller
public class EntryPointController {

    /**
     * Retona a página inicial da aplicação.
     * @return {String} pagina inicial
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "index";
    }


}
