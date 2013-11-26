package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.Cliente;
import br.com.lawyer.core.repository.ClienteRepository;
import br.com.lawyer.core.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 22/11/2013
 */
@Service
public class ClienteServiceImpl extends BaseServiceImpl<String, Cliente, ClienteRepository> implements ClienteService {

    @Autowired
    public ClienteServiceImpl (ClienteRepository repository) {
        super(repository);
    }

    @Override
    public Page<Cliente> findClientes (String query, PageRequest pageRequest) {
        return getRepository().findClientes(query, pageRequest);
    }

    @Override
    public void removerPorReferenciaUid (String uid, Class<?> klazz) {
        getRepository().remover(uid, klazz);
    }


}
