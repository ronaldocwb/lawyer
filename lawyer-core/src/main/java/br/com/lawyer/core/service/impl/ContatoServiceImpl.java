package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.Contato;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.ContatoRepository;
import br.com.lawyer.core.repository.predicates.ContatoPredicate;
import br.com.lawyer.core.service.ContatoService;
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
public class ContatoServiceImpl extends BaseServiceImpl<String, Contato, ContatoRepository> implements ContatoService {

    private static final Logger logger = Logger.getLogger(ContatoService.class);

    @Autowired
    public ContatoServiceImpl (ContatoRepository repository) {
        super(repository);
    }

    @Override
    public Page<Contato> findContatos (String query, String tipo, PageRequest pageRequest) {
        Page<Contato> clientes;
        if (StringUtils.isBlank(query)) {
            clientes = getRepository().findAll(ContatoPredicate.buscaPorTipo(tipo), pageRequest);
        } else {
            clientes = getRepository().findClientes(query, tipo, pageRequest);
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

    @Override
    public Page<Contato> findContatosPessoas (String query, PageRequest pageRequest) {
        return getRepository().findAll(ContatoPredicate.buscarPorPessoasLike(query), pageRequest);
    }

    @Override
    public Page<Contato> findContatosEmpresas (String query, PageRequest pageRequest) {
        return getRepository().findAll(ContatoPredicate.buscarPorEmpresasLike(query), pageRequest);
    }


}
