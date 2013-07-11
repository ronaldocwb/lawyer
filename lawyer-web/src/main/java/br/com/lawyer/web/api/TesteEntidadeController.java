package br.com.lawyer.web.api;

import br.com.lawyer.core.entity.EntidadeTeste;
import br.com.lawyer.core.repository.EntidadeTesteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TesteEntidadeController {

    @Autowired
    private EntidadeTesteRepository repository;

    @RequestMapping(value = "/rest/teste", method = RequestMethod.GET)
    public @ResponseBody EntidadeTeste teste() {
        EntidadeTeste teste = new EntidadeTeste();
        teste = repository.saveAndFlush(teste);
        return teste;

    }
}
