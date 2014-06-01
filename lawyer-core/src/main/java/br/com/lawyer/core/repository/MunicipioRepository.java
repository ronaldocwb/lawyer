package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.JPABaseRepository;
import br.com.lawyer.core.entity.common.Municipio;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Deividi
 * @since 07/10/2013
 */
@Repository
@Transactional
public interface MunicipioRepository extends JPABaseRepository<Long, Municipio> {

    List<Municipio> findByNmMunicipioContaining(String query);
}
