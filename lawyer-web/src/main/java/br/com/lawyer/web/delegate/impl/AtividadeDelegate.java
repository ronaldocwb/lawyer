package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Atividade;
import br.com.lawyer.core.service.IAtividadeService;
import br.com.lawyer.web.base.BaseDelegate;
import br.com.lawyer.web.delegate.IAtividadeDelegate;
import br.com.lawyer.web.vo.AtividadeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Deividi
 * @since 11/11/2013
 */
@Service
public class AtividadeDelegate extends BaseDelegate<Atividade, AtividadeVO> implements IAtividadeDelegate {

    @Autowired
    private IAtividadeService advogadoService;

    @Transactional
    @Override
    public Page<AtividadeVO> findAtividadePorPagina (String query, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<Atividade> pessoas = advogadoService.findAll(pageRequest);
        return getVO(pessoas, pageRequest);
    }

    @Override
    public AtividadeVO salvar (AtividadeVO advogadoVO) {
        Atividade pessoa = advogadoService.save(advogadoVO.parse());
        return getVO(pessoa);
    }

    @Override
    public void deletar (String uid) {
        advogadoService.delete(uid);
    }

    @Override
    public AtividadeVO atualizar (AtividadeVO advogadoVO, String uid) {
        Atividade pessoa = advogadoService.save(advogadoVO.parse());
        return getVO(pessoa);
    }

    @Override
    public AtividadeVO buscarPorUid (String uid) {
        Atividade pessoa = advogadoService.findOne(uid);
        return getVO(pessoa);
    }
}
