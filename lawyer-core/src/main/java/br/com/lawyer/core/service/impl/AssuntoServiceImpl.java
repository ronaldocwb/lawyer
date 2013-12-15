package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.Assunto;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.AssuntoRepository;
import br.com.lawyer.core.service.AssuntoService;
import br.com.lawyer.core.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * TODO inserir javadoc
     * @param nome
     * @param pageRequest
     * @return
     */
    @Override
    public Page<Assunto> findAssuntoPorNomeOuPagina (String nome, PageRequest pageRequest) {
        return getRepository().findAll(nomeIsLike(nome), pageRequest);
    }

    /**
     * Lista os assuntos por um UID de cliente.
     * Se o cliente não for informado, lança uma {@link br.com.lawyer.core.exception.BusinessException}
     * TODO Verificar as permissões de acesso a esses assuntos pelo cliente logadao
     * @param uid
     * @param pageRequest
     * @return Page<Assunto>
     * @throws BusinessException
     */
    @Override
    @Transactional
    public Page<Assunto> findAssuntosPorClienteUid (String uid, PageRequest pageRequest) throws BusinessException {

        // TODO Verificar as permissões de acesso a esses assuntos pelo cliente logadao

        if (StringUtils.isBlank(uid)) {
            throw new BusinessException("UID do assunto não pode ser nulo ou vazio.");
        }
        uid = "";
//        return getRepository().findAll(AssuntoPredicate.clienteUidEqualsTo(uid), pageRequest);
        return getRepository().findAll(pageRequest);
    }
}
