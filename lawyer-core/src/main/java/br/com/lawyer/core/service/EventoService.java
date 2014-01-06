package br.com.lawyer.core.service;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Evento;
import br.com.lawyer.core.repository.EventoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Deividi
 * @since 20/12/2013
 */
public interface EventoService extends BaseService<String, Evento, EventoRepository> {
    Page<Evento> findEventoPorPagina (String query, PageRequest pageRequest);
}
