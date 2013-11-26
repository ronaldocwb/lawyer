package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.JPABaseRepository;
import br.com.lawyer.core.entity.Assunto;
import org.springframework.stereotype.Repository;

/**
 * @author Deividi
 * @since 15/11/2013
 */
@Repository
public interface AssuntoRepository extends JPABaseRepository<String,Assunto> {
}
