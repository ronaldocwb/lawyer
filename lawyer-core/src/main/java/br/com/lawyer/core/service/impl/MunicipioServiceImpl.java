package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.common.Municipio;
import br.com.lawyer.core.repository.MunicipioRepository;
import br.com.lawyer.core.repository.predicates.MunicipioPredicate;
import br.com.lawyer.core.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 07/10/2013
 */
@Service
public class MunicipioServiceImpl extends BaseServiceImpl<Long, Municipio, MunicipioRepository> implements MunicipioService {
    /**
     * Construtor
     *
     * @param repository - DAO que ser? utilizado referente a entidade manipulada
     */
    @Autowired
    public MunicipioServiceImpl (MunicipioRepository repository) {
        super(repository);
    }

    @Override
    public Page<Municipio> buscaPorNomeContendo (String query, PageRequest pageRequest) {
        return getRepository().findAll(MunicipioPredicate.nomeIsLike(query), pageRequest);
    }
}
