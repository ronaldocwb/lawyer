package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.Assunto;
import br.com.lawyer.core.repository.AssuntoRepository;
import br.com.lawyer.core.service.AssuntoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static br.com.lawyer.core.repository.predicates.AssuntoPredicate.nomeIsLike;

/**
 * @author Deividi
 * @since 15/11/2013
 */
@Service
public class AssuntoServiceImpl extends BaseServiceImpl<String, Assunto, AssuntoRepository> implements AssuntoService {

    @Autowired
    public AssuntoServiceImpl (AssuntoRepository repository) {
        super(repository);
    }

    @Override
    public Page<Assunto> findAssuntoPorNomeOuPagina (String nome, PageRequest pageRequest) {
        return getRepository().findAll(nomeIsLike(nome), pageRequest);
    }
}
