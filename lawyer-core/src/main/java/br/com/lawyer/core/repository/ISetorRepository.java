package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.IJPABaseRepository;
import br.com.lawyer.core.entity.Setor;
import org.springframework.stereotype.Repository;

/**
 * @author Deividi
 * @since 03/11/2013
 */
@Repository
public interface ISetorRepository extends IJPABaseRepository<String, Setor> {
}
