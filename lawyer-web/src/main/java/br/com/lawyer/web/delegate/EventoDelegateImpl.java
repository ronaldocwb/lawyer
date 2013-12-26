package br.com.lawyer.web.delegate;

import br.com.lawyer.core.entity.Evento;
import br.com.lawyer.core.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 20/12/2013
 */
@Service
public class EventoDelegateImpl implements EventoDelegate {

    @Autowired
    private EventoService eventoService;

    @Override
    public Page<Evento> findEventoPorPagina (String query, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        return eventoService.findEventoPorPagina(query, pageRequest);
    }

    @Override
    public Evento salvar (Evento evento) {
        return eventoService.save(evento);
    }

    @Override
    public void deletar (String uid) {
        eventoService.delete(uid);
    }

    @Override
    public Evento atualizar (Evento evento, String uid) {
        return eventoService.save(evento);
    }
}
