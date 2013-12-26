package br.com.lawyer.web.api;

import br.com.lawyer.core.entity.Evento;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.annotation.ApiController;
import br.com.lawyer.web.delegate.EventoDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Deividi
 * @since 20/12/2013
 */
@ApiController
public class EventoController {

    @Autowired
    private EventoDelegate eventoDelegate;

    @RequestMapping (value = "/eventos", method = RequestMethod.GET)
    public @ResponseBody
    Page<Evento> list(
            @RequestParam (value = "q", required = false) String q,
            @RequestParam (value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "limit", defaultValue = "25", required = false) int limit) {
        return eventoDelegate.findEventoPorPagina(q, page, limit);
    }

    @RequestMapping(value = "/eventos", method = RequestMethod.POST)
    public @ResponseBody Evento salvarEvento(@RequestBody Evento evento) throws BusinessException {
        return eventoDelegate.salvar(evento);
    }

    @RequestMapping(value = "/eventos/{uid}", method = RequestMethod.DELETE)
    public ResponseEntity excluir(@PathVariable ("uid") String uid) {
        eventoDelegate.deletar(uid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/eventos/{uid}", method = RequestMethod.PUT)
    public @ResponseBody Evento update(@PathVariable("uid") String uid, @RequestBody Evento evento) throws BusinessException {
        return eventoDelegate.atualizar(evento, uid);
    }
}
