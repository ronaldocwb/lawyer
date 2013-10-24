package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.IJPABaseRepository;
import br.com.lawyer.core.entity.Responsavel;
import org.springframework.stereotype.Repository;

/**
 * @author Deividi
 * @since 22/10/2013
 */
@Repository
public interface IResponsavelRepository extends IJPABaseRepository<String, Responsavel> {
}
