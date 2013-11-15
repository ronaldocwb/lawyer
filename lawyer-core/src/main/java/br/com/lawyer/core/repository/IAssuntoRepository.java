package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.IJPABaseRepository;
import br.com.lawyer.core.entity.Assunto;
import org.springframework.stereotype.Repository;

/**
 * @author Deividi
 * @since 15/11/2013
 */
@Repository
public interface IAssuntoRepository extends IJPABaseRepository<String,Assunto> {
}
