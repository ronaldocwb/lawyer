package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.Cliente;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.ClienteRepository;
import br.com.lawyer.core.service.ClienteService;
import br.com.lawyer.core.util.StringUtils;
import org.apache.log4j.Logger;
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

    private static final Logger logger = Logger.getLogger(ClienteService.class);

    @Autowired
    public ClienteServiceImpl (ClienteRepository repository) {
        super(repository);
    }

    @Override
    public Page<Cliente> findClientes (String query, PageRequest pageRequest) {
        Page<Cliente> clientes;
        if (StringUtils.isBlank(query)) {
            clientes = getRepository().findAll(pageRequest);
        } else {
            clientes = getRepository().findClientes(query, pageRequest);
        }
        return clientes;
    }

    @Override
    public void removerPorReferenciaUid (String uid, Class<?> klazz) throws BusinessException {
        if (StringUtils.isBlank(uid)) {
            throw new BusinessException("UID da referencia deve ser informado para apagar um Cliente.");
        }
        if (klazz.equals(null)) {
            throw new BusinessException("Classe de referencia deve ser informada para apagar um Cliente.");
        }
        logger.info(String.format("Apagando o Cliente do tipo %s, da classe %s, pelo usuário %s", uid, klazz.getSimpleName(), getUsuarioLogado().getEmail()));
        getRepository().remover(uid, klazz);
    }


}
