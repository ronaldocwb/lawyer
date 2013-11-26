package br.com.lawyer.core.service;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Lembrete;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.LembreteRepository;

import java.util.List;

/**
 * @author Deividi
 * @since 22/10/2013
 */
public interface LembreteService extends BaseService<String, Lembrete, LembreteRepository> {
    List<Lembrete> findAllByCurrentUser () throws BusinessException;

    Lembrete salvar (Lembrete parse) throws BusinessException;

    void deleteTodos () throws BusinessException;


}
