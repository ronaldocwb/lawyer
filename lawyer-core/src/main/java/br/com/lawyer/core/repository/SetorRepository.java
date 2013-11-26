package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.JPABaseRepository;
import br.com.lawyer.core.entity.Setor;
import org.springframework.stereotype.Repository;

/**
 * @author Deividi
 * @since 03/11/2013
 */
@Repository
public interface SetorRepository extends JPABaseRepository<String, Setor> {
}
