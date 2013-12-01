package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Advogado;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.service.AdvogadoService;
import br.com.lawyer.web.base.BaseDelegate;
import br.com.lawyer.web.delegate.AdvogadoDelegate;
import br.com.lawyer.web.vo.AdvogadoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ronaldo
 * @since 23/10/2013
 */
@Service
public class AdvogadoDelegateImpl extends BaseDelegate<Advogado, AdvogadoVO> implements AdvogadoDelegate {

    @Autowired
    private AdvogadoService advogadoService;

    @Transactional
    @Override
    public Page<AdvogadoVO> findAdvogadoPorPagina (String query, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<Advogado> advogados = advogadoService.buscarPorNomeLike(query, pageRequest);
        return getVO(advogados, pageRequest);
    }

    @Transactional
    @Override
    public AdvogadoVO salvar (AdvogadoVO advogadoVO) throws BusinessException {
        Advogado advogado = advogadoService.salvarAdvogado(advogadoVO.parse());
        return getVO(advogado);
    }

    @Override
    public void deletar (String uid) {
        advogadoService.delete(uid);
    }

    @Override
    public AdvogadoVO atualizar (AdvogadoVO advogadoVO, String uid) {
        Advogado advogado = advogadoService.save(advogadoVO.parse());
        return getVO(advogado);
    }

    @Override
    public AdvogadoVO buscarPorUid (String uid) {
        Advogado advogado = advogadoService.findOne(uid);
        return getVO(advogado);
    }

    @Override
    public AdvogadoVO buscarAdvogadoPorPessoaUid (String pessoaUid) {
        Advogado advogado = advogadoService.buscarAdvogadoPorPessoaUid(pessoaUid);
        return getVO(advogado);
    }
}
