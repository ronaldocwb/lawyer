package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Advogado;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.service.AdvogadoService;
import br.com.lawyer.web.delegate.AdvogadoDelegate;
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
public class AdvogadoDelegateImpl implements AdvogadoDelegate {

    @Autowired
    private AdvogadoService advogadoService;

    @Transactional
    @Override
    public Page<Advogado> findAdvogadoPorPagina (String query, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<Advogado> advogados = advogadoService.buscarPorNomeLike(query, pageRequest);
        return advogados;
    }

    @Transactional
    @Override
    public Advogado salvar (Advogado advogadoVO) throws BusinessException {
        Advogado advogado = advogadoService.salvarAdvogado(advogadoVO);
        return advogado;
    }

    @Override
    public void deletar (String uid) {
        advogadoService.delete(uid);
    }

    @Override
    public Advogado atualizar (Advogado advogadoVO, String uid) {
        Advogado advogado = advogadoService.save(advogadoVO);
        return advogado;
    }

    @Override
    public Advogado buscarPorUid (String uid) {
        Advogado advogado = advogadoService.findOne(uid);
        return advogado;
    }

    @Override
    public Advogado buscarAdvogadoPorPessoaUid (String pessoaUid) {
        Advogado advogado = advogadoService.buscarAdvogadoPorPessoaUid(pessoaUid);
        return advogado;
    }
}
