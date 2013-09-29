package br.com.lawyer.core.repository;

import br.com.lawyer.core.base.IJPABaseRepository;
import br.com.lawyer.core.entity.Empresa;
import org.springframework.stereotype.Repository;

/**
 * @author Deividi
 * @since 26/09/2013
 */
@Repository
public interface IEmpresaRepository extends IJPABaseRepository<String, Empresa> {
}
