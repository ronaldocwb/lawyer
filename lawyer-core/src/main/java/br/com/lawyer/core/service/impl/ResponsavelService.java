package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Responsavel;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.IResponsavelRepository;
import br.com.lawyer.core.repository.predicates.ResponsavelPredicate;
import br.com.lawyer.core.service.IResponsavelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 22/10/2013
 */
@Service
public class ResponsavelService extends BaseService<String, Responsavel, IResponsavelRepository> implements IResponsavelService {
    /**
     * Construtor
     *
     * @param repository - DAO que ser? utilizado referente a entidade manipulada
     */
    @Autowired
    public ResponsavelService (IResponsavelRepository repository) {
        super(repository);
    }

    @Override
    public Page<Responsavel> buscarPorCampoLike (String query, Pageable pageRequest) throws BusinessException {
        return getRepository().findAll(ResponsavelPredicate.isNomePessoaLike(query), pageRequest);
    }

}
