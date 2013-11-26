package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.Responsavel;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.ResponsavelRepository;
import br.com.lawyer.core.repository.predicates.ResponsavelPredicate;
import br.com.lawyer.core.service.ResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 22/10/2013
 */
@Service
public class ResponsavelServiceImpl extends BaseServiceImpl<String, Responsavel, ResponsavelRepository> implements ResponsavelService {
    /**
     * Construtor
     *
     * @param repository - DAO que ser? utilizado referente a entidade manipulada
     */
    @Autowired
    public ResponsavelServiceImpl (ResponsavelRepository repository) {
        super(repository);
    }

    @Override
    public Page<Responsavel> buscarPorCampoLike (String query, Pageable pageRequest) throws BusinessException {
        return getRepository().findAll(ResponsavelPredicate.isNomePessoaLike(query), pageRequest);
    }

}
