package br.com.lawyer.web.delegate;

import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.vo.LembreteVO;

import java.util.List;

/**
 * @author Deividi
 * @since 10/10/2013
 */
public interface ILembreteDelegate {

    LembreteVO salvar (LembreteVO lembreteVO) throws BusinessException;

    void deletar (LembreteVO lembrete);

    List<LembreteVO> findAll () throws BusinessException;

    LembreteVO atualizar (LembreteVO lembrete) throws BusinessException;

    void deletarTodos () throws BusinessException;

}
