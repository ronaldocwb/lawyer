package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.AreaAtuacao;
import br.com.lawyer.core.service.AreaAtuacaoService;
import br.com.lawyer.web.base.BaseDelegate;
import br.com.lawyer.web.delegate.AreaAtuacaoDelegate;
import br.com.lawyer.web.vo.AreaAtuacaoVO;
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
public class AreaAtuacaoDelegateImpl extends BaseDelegate<AreaAtuacao, AreaAtuacaoVO> implements AreaAtuacaoDelegate {

    @Autowired
    private AreaAtuacaoService areaAtuacaoService;

    @Transactional
    @Override
    public Page<AreaAtuacaoVO> findAreaAtuacaoPorPagina (String query, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<AreaAtuacao> areaAtuacao = areaAtuacaoService.buscarPorNomeLike(query, pageRequest);
        return getVO(areaAtuacao, pageRequest);
    }

    @Override
    public AreaAtuacaoVO salvar (AreaAtuacaoVO areaAtuacaoVO) {
        AreaAtuacao areaAtuacao = areaAtuacaoService.save(areaAtuacaoVO.parse());
        return getVO(areaAtuacao);
    }

    @Override
    public void deletar (String uid) {
        areaAtuacaoService.delete(uid);
    }

    @Override
    public AreaAtuacaoVO atualizar (AreaAtuacaoVO areaAtuacaoVO, String uid) {
        AreaAtuacao areaAtuacao = areaAtuacaoService.save(areaAtuacaoVO.parse());
        return getVO(areaAtuacao);
    }

    @Override
    public AreaAtuacaoVO buscarPorUid (String uid) {
        AreaAtuacao areaAtuacao = areaAtuacaoService.findOne(uid);
        return getVO(areaAtuacao);
    }
}
