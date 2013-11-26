package br.com.lawyer.web.delegate;

import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.vo.AdvocaciaVO;

/**
 * @author Deividi
 * @since 31/10/2013
 */
public interface AdvocaciaDelegate {
    AdvocaciaVO findAdvocaciaUsuario () throws BusinessException;

}
