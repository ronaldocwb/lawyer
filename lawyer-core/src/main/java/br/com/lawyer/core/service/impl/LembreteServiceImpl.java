package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.Lembrete;
import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.LembreteRepository;
import br.com.lawyer.core.service.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Deividi
 * @since 22/10/2013
 */
@Service
public class LembreteServiceImpl extends BaseServiceImpl<String, Lembrete, LembreteRepository> implements LembreteService {
    /**
     * Construtor
     *
     * @param repository - DAO que ser? utilizado referente a entidade manipulada
     */
    @Autowired
    public LembreteServiceImpl (LembreteRepository repository) {
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

    @Override
    public void deleteTodos () throws BusinessException {
        Usuario usuario = getUsuarioLogado();
        getRepository().deletarTodosLembretesUsuario(usuario.getUid());
    }

}
