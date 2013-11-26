package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.Setor;
import br.com.lawyer.core.repository.SetorRepository;
import br.com.lawyer.core.repository.predicates.SetorPredicate;
import br.com.lawyer.core.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 03/11/2013
 */
@Service
public class SetorServiceImpl extends BaseServiceImpl<String, Setor, SetorRepository> implements SetorService {
    /**
     * Construtor
     *
     * @param repository - DAO que ser? utilizado referente a entidade manipulada
     */
    @Autowired
    public SetorServiceImpl (SetorRepository repository) {
        super(repository);
    }

    @Override
    public Page<Setor> findByNome (String query, PageRequest pageRequest) {
        return getRepository().findAll(SetorPredicate.nomeIsLike(query), pageRequest);
    }
}
