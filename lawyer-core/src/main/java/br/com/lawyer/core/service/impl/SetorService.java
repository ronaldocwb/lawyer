package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Setor;
import br.com.lawyer.core.repository.ISetorRepository;
import br.com.lawyer.core.service.ISetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 03/11/2013
 */
@Service
public class SetorService extends BaseService<String, Setor, ISetorRepository> implements ISetorService {
    /**
     * Construtor
     *
     * @param repository - DAO que ser? utilizado referente a entidade manipulada
     */
    @Autowired
    public SetorService (ISetorRepository repository) {
        super(repository);
    }
}
