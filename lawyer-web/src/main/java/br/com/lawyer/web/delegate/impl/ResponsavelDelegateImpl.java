package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Responsavel;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.service.ResponsavelService;
import br.com.lawyer.web.base.BaseDelegate;
import br.com.lawyer.web.delegate.ResponsavelDelegate;
import br.com.lawyer.web.vo.ResponsavelVO;
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
public class ResponsavelDelegateImpl extends BaseDelegate<Responsavel, ResponsavelVO> implements ResponsavelDelegate {

    @Autowired
    private ResponsavelService responsavelService;

    @Override
    public Page<ResponsavelVO> findResponsavelPorPagina (String query, int page, int limit) throws BusinessException {
        Pageable pageRequest = new PageRequest(page, limit);
        Page<Responsavel> pessoas = responsavelService.buscarPorCampoLike(query, pageRequest);
        return getVO(pessoas, pageRequest);
    }

    @Override
    public ResponsavelVO salvar (ResponsavelVO pessoaVO) {
        Responsavel pessoa = responsavelService.save(pessoaVO.parse());
        return getVO(pessoa);
    }

    @Override
    public void deletar (String uid) {
        responsavelService.delete(uid);
    }

    @Override
    public ResponsavelVO atualizar (ResponsavelVO responsavelVO, String uid) {
        Responsavel pessoa = responsavelService.save(responsavelVO.parse());
        return getVO(pessoa);
    }

    @Override
    public ResponsavelVO buscarPorUid (String uid) {
        Responsavel pessoa = responsavelService.findOne(uid);
        return getVO(pessoa);
    }
}
