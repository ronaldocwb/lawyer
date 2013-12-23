package br.com.lawyer.core.service;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Contato;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.ContatoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Deividi
 * @since 22/11/2013
 */
public interface ContatoService extends BaseService<String, Contato, ContatoRepository> {
    Page<Contato> findContatos (String query, String tipo, PageRequest pageRequest);

    void removerPorReferenciaUid (String uid, Class<?> klazz) throws BusinessException;

    Page<Contato> findContatosPessoas (String query, PageRequest pageRequest);

    Page<Contato> findContatosEmpresas (String query, PageRequest pageRequest);

    Page<Contato> findContatosUsuarios (String query, PageRequest pageRequest);

}
