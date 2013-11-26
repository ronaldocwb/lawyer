package br.com.lawyer.core.service;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Advocacia;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.AdvocaciaRepository;

/**
 * @author Deividi
 * @since 31/10/2013
 */
public interface AdvocaciaService extends BaseService<String, Advocacia, AdvocaciaRepository> {

    Advocacia findAdvocaciaUsuarioLogado () throws BusinessException;

}
