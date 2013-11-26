package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.JPABaseRepository;
import br.com.lawyer.core.entity.Advocacia;
import org.springframework.stereotype.Repository;

/**
 * @author Deividi
 * @since 31/10/2013
 */
@Repository
public interface AdvocaciaRepository extends JPABaseRepository<String, Advocacia> {

}
