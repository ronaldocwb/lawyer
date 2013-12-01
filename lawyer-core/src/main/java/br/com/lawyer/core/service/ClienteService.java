package br.com.lawyer.core.service;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Cliente;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Deividi
 * @since 22/11/2013
 */
public interface ClienteService extends BaseService<String, Cliente, ClienteRepository> {
    Page<Cliente> findClientes (String query, PageRequest pageRequest);

    void removerPorReferenciaUid (String uid, Class<?> klazz) throws BusinessException;

}
