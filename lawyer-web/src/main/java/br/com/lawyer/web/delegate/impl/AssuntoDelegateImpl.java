package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Assunto;
import br.com.lawyer.core.service.AssuntoService;
import br.com.lawyer.web.base.BaseDelegate;
import br.com.lawyer.web.delegate.AssuntoDelegate;
import br.com.lawyer.web.vo.AssuntoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 15/11/2013
 */
@Service
public class AssuntoDelegateImpl extends BaseDelegate<Assunto, AssuntoVO> implements AssuntoDelegate {

    @Autowired
    private AssuntoService assuntoService;

    @Override
    public Page<AssuntoVO> findAssuntoPorNomeOuPagina (String nome, int page, int limit) {
        PageRequest pageRequest = new PageRequest(page, limit);
        Page<Assunto> assuntos = assuntoService.findAssuntoPorNomeOuPagina(nome, pageRequest);
        return getVO(assuntos, pageRequest);
    }

    @Override
    public AssuntoVO salvar (AssuntoVO assuntoVO) {
        Assunto assunto = assuntoService.save(assuntoVO.parse());
        return getVO(assunto);
    }

    @Override
    public void deletar (String uid) {
        assuntoService.delete(uid);
    }

    @Override
    public AssuntoVO update (AssuntoVO assuntoVO, String uid) {
        Assunto assunto = assuntoService.save(assuntoVO.parse());
        return getVO(assunto);
    }

    @Override
    public AssuntoVO findOne (String uid) {
        Assunto assunto = assuntoService.findOne(uid);
        return getVO(assunto);
    }
}
