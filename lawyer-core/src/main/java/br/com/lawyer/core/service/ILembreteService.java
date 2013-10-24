package br.com.lawyer.core.service;

import br.com.lawyer.core.base.IBaseService;
import br.com.lawyer.core.entity.Lembrete;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.ILembreteRepository;

import java.util.List;

/**
 * @author Deividi
 * @since 22/10/2013
 */
public interface ILembreteService extends IBaseService<String, Lembrete, ILembreteRepository> {
    List<Lembrete> findAllByCurrentUser () throws BusinessException;

}
