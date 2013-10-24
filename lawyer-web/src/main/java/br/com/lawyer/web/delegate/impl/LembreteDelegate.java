package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Lembrete;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.service.ILembreteService;
import br.com.lawyer.web.base.BaseDelegate;
import br.com.lawyer.web.delegate.ILembreteDelegate;
import br.com.lawyer.web.vo.LembreteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Deividi
 * @since 22/10/2013
 */
@Service
public class LembreteDelegate extends BaseDelegate<Lembrete, LembreteVO> implements ILembreteDelegate {

    @Autowired
    private ILembreteService lembreteService;

    @Override
    @Transactional
    public LembreteVO salvar (LembreteVO lembreteVO) {
        Lembrete lembrete = lembreteService.save(lembreteVO.parse());
        return getVO(lembrete);
    }

    @Override
    public void deletar (Iterable<LembreteVO> lembretesVO) {
        List<Lembrete> lembretes = new ArrayList<>();
        for (LembreteVO vo : lembretesVO) {
            lembretes.add(vo.parse());
        }
        lembreteService.deleteInBatch(lembretes);
    }

    @Override
    public List<LembreteVO> findAll () throws BusinessException {
        List<Lembrete> lembretes = lembreteService.findAllByCurrentUser();
        return getVO(lembretes);
    }
}
