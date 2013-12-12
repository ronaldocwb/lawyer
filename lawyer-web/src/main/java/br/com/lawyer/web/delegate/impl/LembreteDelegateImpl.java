package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Lembrete;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.service.LembreteService;
import br.com.lawyer.web.delegate.LembreteDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Deividi
 * @since 22/10/2013
 */
@Service
public class LembreteDelegateImpl implements LembreteDelegate {

    @Autowired
    private LembreteService lembreteService;

    @Override
    @Transactional
    public Lembrete salvar (Lembrete lembreteVO) throws BusinessException {
        Lembrete lembrete = lembreteService.salvar(lembreteVO);
        return lembrete;
    }

    @Override
    public void deletar (Lembrete lembretesVO) {
        lembreteService.delete(lembretesVO);
    }

    @Override
    public List<Lembrete> findAll () throws BusinessException {
        List<Lembrete> lembretes = lembreteService.findAllByCurrentUser();
        return lembretes;
    }

    @Override
    public Lembrete atualizar (Lembrete lembreteVO) throws BusinessException {
        Lembrete lembrete = lembreteService.salvar(lembreteVO);
        return lembrete;
    }

    @Override
    public void deletarTodos () throws BusinessException {
        lembreteService.deleteTodos();
    }
}
