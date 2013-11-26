package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.Advocacia;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.AdvocaciaRepository;
import br.com.lawyer.core.service.AdvocaciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 31/10/2013
 */
@Service
public class AdvocaciaServiceImpl extends BaseServiceImpl<String, Advocacia, AdvocaciaRepository> implements AdvocaciaService {

    @Autowired
    public AdvocaciaServiceImpl (AdvocaciaRepository repository) {
        super(repository);
    }

    @Override
    public Advocacia findAdvocaciaUsuarioLogado () throws BusinessException {
        return findOne(getUsuarioLogado().getAdvocacia().getUid());
    }
}
