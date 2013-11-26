package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.JPABaseRepository;
import br.com.lawyer.core.entity.Responsavel;
import org.springframework.stereotype.Repository;

/**
 * @author Deividi
 * @since 22/10/2013
 */
@Repository
public interface ResponsavelRepository extends JPABaseRepository<String, Responsavel> {
}
