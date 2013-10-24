package br.com.lawyer.web.delegate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lawyer.core.entity.Advogado;
import br.com.lawyer.core.service.IAdvogadoService;
import br.com.lawyer.web.base.BaseDelegate;
import br.com.lawyer.web.delegate.IAdvogadoDelegate;
import br.com.lawyer.web.vo.AdvogadoVO;

/**
 * @author Ronaldo
 * @since 23/10/2013
 */
@Service
public class AdvogadoDelegate extends BaseDelegate<Advogado, AdvogadoVO> implements IAdvogadoDelegate {

    @Autowired
    private IAdvogadoService advogadoService;

    @Transactional
    @Override
    public Page<AdvogadoVO> findAdvogadoPorPagina (String query, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<Advogado> pessoas = advogadoService.buscarPorNomeLike(query, pageRequest);
        return getVO(pessoas, pageRequest);
    }

    @Override
    public AdvogadoVO salvar (AdvogadoVO advogadoVO) {
        Advogado pessoa = advogadoService.save(advogadoVO.parse());
        return getVO(pessoa);
    }

    @Override
    public void deletar (String uid) {
        advogadoService.delete(uid);
    }

    @Override
    public AdvogadoVO atualizar (AdvogadoVO advogadoVO, String uid) {
        Advogado pessoa = advogadoService.save(advogadoVO.parse());
        return getVO(pessoa);
    }

    @Override
    public AdvogadoVO buscarPorUid (String uid) {
        Advogado pessoa = advogadoService.findOne(uid);
        return getVO(pessoa);
    }
}
