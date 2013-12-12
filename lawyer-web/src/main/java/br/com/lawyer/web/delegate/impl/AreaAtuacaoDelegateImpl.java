package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.AreaAtuacao;
import br.com.lawyer.core.service.AreaAtuacaoService;
import br.com.lawyer.web.delegate.AreaAtuacaoDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Deividi
 * @since 26/09/2013
 */
@Service
public class AreaAtuacaoDelegateImpl implements AreaAtuacaoDelegate {

    @Autowired
    private AreaAtuacaoService areaAtuacaoService;

    @Transactional
    @Override
    public Page<AreaAtuacao> findAreaAtuacaoPorPagina (String query, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<AreaAtuacao> areaAtuacao = areaAtuacaoService.buscarPorNomeLike(query, pageRequest);
        return areaAtuacao;
    }

    @Override
    public AreaAtuacao salvar (AreaAtuacao areaAtuacao) {
        return areaAtuacaoService.save(areaAtuacao);
    }

    @Override
    public void deletar (String uid) {
        areaAtuacaoService.delete(uid);
    }

    @Override
    public AreaAtuacao atualizar (AreaAtuacao areaAtuacao, String uid) {
        return areaAtuacaoService.save(areaAtuacao);
    }

    @Override
    public AreaAtuacao buscarPorUid (String uid) {
        AreaAtuacao areaAtuacao = areaAtuacaoService.findOne(uid);
        return areaAtuacao;
    }
}
