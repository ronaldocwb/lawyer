package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.Evento;
import br.com.lawyer.core.repository.EventoRepository;
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
public class EventoServiceImpl extends BaseServiceImpl<String, Evento,EventoRepository> implements EventoService {

    @Autowired
    public EventoServiceImpl (EventoRepository repository) {
        super(repository);
    }

    @Override
    public Page<Evento> findEventoPorPagina (String query, PageRequest pageRequest) {
        return null;
    }
}
