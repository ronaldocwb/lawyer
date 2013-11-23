package br.com.lawyer.core.service;

import br.com.lawyer.core.base.IBaseService;
import br.com.lawyer.core.entity.Advocacia;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.IAdvocaciaRepository;

/**
 * @author Deividi
 * @since 31/10/2013
 */
public interface IAdvocaciaService extends IBaseService<String, Advocacia, IAdvocaciaRepository> {

    Advocacia findAdvocaciaUsuarioLogado () throws BusinessException;

}
