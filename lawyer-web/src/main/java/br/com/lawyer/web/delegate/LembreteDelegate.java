package br.com.lawyer.web.delegate;

import br.com.lawyer.core.entity.Lembrete;
import br.com.lawyer.core.exception.BusinessException;

import java.util.List;

/**
 * @author Deividi
 * @since 10/10/2013
 */
public interface LembreteDelegate {

    Lembrete salvar (Lembrete lembreteVO) throws BusinessException;

    void deletar (Lembrete lembrete);

    List<Lembrete> findAll () throws BusinessException;

    Lembrete atualizar (Lembrete lembrete) throws BusinessException;

    void deletarTodos () throws BusinessException;

}
