package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.common.Municipio;
import br.com.lawyer.core.repository.IMunicipioRepository;
import br.com.lawyer.core.service.IMunicipioService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Deividi
 * @since 07/10/2013
 */
@Service
public class MunicipioService extends BaseService<Long, Municipio, IMunicipioRepository> implements IMunicipioService {
    /**
     * Construtor
     *
     * @param repository - DAO que ser? utilizado referente a entidade manipulada
     */
    @Autowired
    public MunicipioService (IMunicipioRepository repository) {
        super(repository);
    }

    @Override
    public List<Municipio> buscaPorNomeContendo (String query) {
        if (StringUtils.isBlank(query)) {
            return null;
        }
        return getRepository().findByNmMunicipioContaining(query.toUpperCase());
    }
}
