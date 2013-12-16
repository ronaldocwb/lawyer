package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Responsavel;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.service.ResponsavelService;
import br.com.lawyer.web.delegate.ResponsavelDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 22/10/2013
 */
@Service
public class ResponsavelDelegateImpl implements ResponsavelDelegate {

    @Autowired
    private ResponsavelService responsavelService;

    @Override
    public Page<Responsavel> findResponsavelPorPagina (String query, int page, int limit) throws BusinessException {
        Pageable pageRequest = new PageRequest(page, limit);
        Page<Responsavel> pessoas = responsavelService.buscarPorCampoLike(query, pageRequest);
        return pessoas;
    }

    @Override
    public Responsavel salvar (Responsavel pessoaVO) {
        Responsavel pessoa = responsavelService.save(pessoaVO);
        return pessoa;
    }

    @Override
    public void deletar (String uid) {
        responsavelService.delete(uid);
    }

    @Override
    public Responsavel atualizar (Responsavel responsavelVO, String uid) {
        Responsavel pessoa = responsavelService.save(responsavelVO);
        return pessoa;
    }

    @Override
    public Responsavel buscarPorUid (String uid) {
        Responsavel pessoa = responsavelService.findOne(uid);
        return pessoa;
    }
}
