package br.com.lawyer.web.delegate;

import br.com.lawyer.core.entity.Evento;
import org.springframework.data.domain.Page;

/**
 * @author Deividi
 * @since 20/12/2013
 */
public interface EventoDelegate {
    Page<Evento> findEventoPorPagina (String query, int page, int limit);

    Evento salvar (Evento evento);

    void deletar (String uid);

    Evento atualizar (Evento evento, String uid);
}
