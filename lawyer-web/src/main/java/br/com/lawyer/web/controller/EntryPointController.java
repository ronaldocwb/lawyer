package br.com.lawyer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        return "authentication/index";
    }


}
