package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Lembrete;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.ILembreteRepository;
import br.com.lawyer.core.service.ILembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Deividi
 * @since 22/10/2013
 */
@Service
public class LembreteService extends BaseService<String, Lembrete, ILembreteRepository> implements ILembreteService {
    /**
     * Construtor
     *
     * @param repository - DAO que ser? utilizado referente a entidade manipulada
     */
    @Autowired
    public LembreteService (ILembreteRepository repository) {
        super(repository);
    }

    @Override
    public List<Lembrete> findAllByCurrentUser () throws BusinessException {
        return getRepository().findByUsuarioUid(super.getUsuarioLogado().getUid());
    }

    @Override
    public Lembrete salvar (Lembrete lembrete) throws BusinessException {
        lembrete.setUsuario(super.getUsuarioLogado());
        return getRepository().save(lembrete);
    }

}
