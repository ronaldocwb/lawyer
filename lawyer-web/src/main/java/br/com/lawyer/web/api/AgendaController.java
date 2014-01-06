package br.com.lawyer.web.api;

import br.com.lawyer.core.entity.Agenda;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.AgendaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Deividi
 * @since 20/12/2013
 */
@ApiController
public class AgendaController {

    @Autowired
    private AgendaDelegate agendaDelegate;

    @RequestMapping (value = "/agendas/usuario", method = RequestMethod.GET)
    public @ResponseBody
    List<Agenda> list() {
        return agendaDelegate.findAgendasPorUsuario();
    }

    @RequestMapping(value = "/agendas", method = RequestMethod.POST)
    public @ResponseBody Agenda salvarAgenda(@RequestBody Agenda agenda) throws BusinessException {
        return agendaDelegate.salvar(agenda);
    }

    @RequestMapping(value = "/agendas/{uid}", method = RequestMethod.DELETE)
    public ResponseEntity excluir(@PathVariable ("uid") String uid) {
        agendaDelegate.deletar(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/agendas/{uid}", method = RequestMethod.PUT)
    public @ResponseBody Agenda update(@PathVariable("uid") String uid, @RequestBody Agenda agenda) throws BusinessException {
        return agendaDelegate.atualizar(agenda, uid);
    }
}