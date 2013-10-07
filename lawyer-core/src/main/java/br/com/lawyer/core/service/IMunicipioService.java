package br.com.lawyer.core.service;

import br.com.lawyer.core.base.IBaseService;
import br.com.lawyer.core.entity.common.Municipio;
import br.com.lawyer.core.repository.IMunicipioRepository;

import java.util.List;

/**
 * @author Deividi
 * @since 07/10/2013
 */
public interface IMunicipioService extends IBaseService<Long, Municipio, IMunicipioRepository> {
    List<Municipio> buscaPorNomeContendo (String query);
}
