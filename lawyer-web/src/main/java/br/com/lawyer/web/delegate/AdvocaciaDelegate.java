package br.com.lawyer.web.delegate;

import br.com.lawyer.core.entity.Advocacia;
import br.com.lawyer.core.exception.BusinessException;

/**
 * @author Deividi
 * @since 31/10/2013
 */
public interface AdvocaciaDelegate {
    Advocacia findAdvocaciaUsuario () throws BusinessException;

}
