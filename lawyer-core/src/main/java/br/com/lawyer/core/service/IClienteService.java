package br.com.lawyer.core.service;

import br.com.lawyer.core.base.IBaseService;
import br.com.lawyer.core.entity.Cliente;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.IClienteRepository;

/**
 * @author Deividi
 * @since 31/10/2013
 */
public interface IClienteService extends IBaseService<String, Cliente, IClienteRepository> {

    Cliente findAdvocaciaUsuarioLogado () throws BusinessException;

}
