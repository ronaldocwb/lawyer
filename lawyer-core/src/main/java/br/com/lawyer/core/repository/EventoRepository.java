package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.JPABaseRepository;
import br.com.lawyer.core.entity.Evento;
import org.springframework.stereotype.Repository;

/**
 * @author Deividi
 * @since 20/12/2013
 */
@Repository
public interface EventoRepository extends JPABaseRepository<String, Evento> {
}
