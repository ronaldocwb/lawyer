package br.com.lawyer.core.service;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.common.Municipio;
import br.com.lawyer.core.repository.MunicipioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Deividi
 * @since 07/10/2013
 */
public interface MunicipioService extends BaseService<Long, Municipio, MunicipioRepository> {
    Page<Municipio> buscaPorNomeContendo (String query, PageRequest pageRequest);
}
