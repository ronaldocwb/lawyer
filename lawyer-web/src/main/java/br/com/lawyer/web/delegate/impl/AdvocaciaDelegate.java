package br.com.lawyer.web.delegate.impl;

import br.com.lawyer.core.entity.Advocacia;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.service.IAdvocaciaService;
import br.com.lawyer.web.base.BaseDelegate;
import br.com.lawyer.web.delegate.IAdvocaciaDelegate;
import br.com.lawyer.web.vo.AdvocaciaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 31/10/2013
 */
@Service
public class AdvocaciaDelegate extends BaseDelegate<Advocacia, AdvocaciaVO> implements IAdvocaciaDelegate {

    @Autowired
    private IAdvocaciaService advocaciaService;

    @Override
    public AdvocaciaVO findAdvocaciaUsuario () throws BusinessException {
        Advocacia advocacia = advocaciaService.findAdvocaciaUsuarioLogado();
        return getVO(advocacia);
    }
}
