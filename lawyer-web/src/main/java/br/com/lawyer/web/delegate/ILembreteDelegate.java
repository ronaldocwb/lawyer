package br.com.lawyer.web.delegate;

import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.vo.LembreteVO;

import java.util.List;

/**
 * @author Deividi
 * @since 10/10/2013
 */
public interface ILembreteDelegate {

    LembreteVO salvar (LembreteVO lembreteVO);

    void deletar (Iterable<LembreteVO> lembretes);

    List<LembreteVO> findAll () throws BusinessException;


}
