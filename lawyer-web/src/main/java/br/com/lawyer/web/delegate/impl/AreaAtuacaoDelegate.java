package br.com.lawyer.web.delegate.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.lawyer.core.entity.AreaAtuacao;
import br.com.lawyer.core.service.IAreaAtuacaoService;
import br.com.lawyer.web.base.BaseDelegate;
import br.com.lawyer.web.delegate.IAreaAtuacaoDelegate;
import br.com.lawyer.web.vo.AreaAtuacaoVO;

/**
 * @author Deividi
 * @since 26/09/2013
 */
@Service
public class AreaAtuacaoDelegate extends BaseDelegate<AreaAtuacao, AreaAtuacaoVO> implements IAreaAtuacaoDelegate {

    @Autowired
    private IAreaAtuacaoService areaAtuacaoService;

    @Transactional
    @Override
    public Page findAreaAtuacaoPorPagina (String query, int page, int limit) {
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
