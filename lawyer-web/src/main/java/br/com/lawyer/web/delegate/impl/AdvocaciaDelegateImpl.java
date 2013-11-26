package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Advocacia;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.service.AdvocaciaService;
import br.com.lawyer.web.base.BaseDelegate;
import br.com.lawyer.web.delegate.AdvocaciaDelegate;
import br.com.lawyer.web.vo.AdvocaciaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 31/10/2013
 */
@Service
public class AdvocaciaDelegateImpl extends BaseDelegate<Advocacia, AdvocaciaVO> implements AdvocaciaDelegate {

    @Autowired
    private AdvocaciaService clienteService;

    @Override
    public AdvocaciaVO findAdvocaciaUsuario () throws BusinessException {
        Advocacia advocacia = clienteService.findAdvocaciaUsuarioLogado();
        return getVO(advocacia);
    }
}
