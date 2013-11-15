package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Cliente;
import br.com.lawyer.core.entity.Usuario;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.IClienteRepository;
import br.com.lawyer.core.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 31/10/2013
 */
@Service
public class ClienteService extends BaseService<String, Cliente, IClienteRepository> implements IClienteService {

    @Autowired
    public ClienteService (IClienteRepository repository) {
        super(repository);
    }

    @Override
    public Cliente findAdvocaciaUsuarioLogado () throws BusinessException {
        Usuario usuario = getUsuarioLogado();
        return getRepository().findOne(usuario.getCliente().getUid());
    }
}
