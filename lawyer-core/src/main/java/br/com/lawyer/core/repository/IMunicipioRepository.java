package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.IJPABaseRepository;
import br.com.lawyer.core.entity.common.Municipio;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Deividi
 * @since 07/10/2013
 */
@Repository
public interface IMunicipioRepository extends IJPABaseRepository<Long, Municipio> {

    List<Municipio> findByNmMunicipioContaining(String query);
}
