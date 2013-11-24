package br.com.lawyer.core.service;

import br.com.lawyer.core.base.IBaseService;
import br.com.lawyer.core.entity.Cliente;
import br.com.lawyer.core.repository.IClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Deividi
 * @since 22/11/2013
 */
public interface IClienteService extends IBaseService<String, Cliente, IClienteRepository> {
    Page<Cliente> findClientes (String query, PageRequest pageRequest);

    void removerPorReferenciaUid (String uid, Class<?> klazz);

    Cliente salvar (Cliente cliente);
}
