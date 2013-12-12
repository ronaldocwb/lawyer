package br.com.lawyer.web.delegate;

import br.com.lawyer.core.entity.Cliente;
import org.springframework.data.domain.Page;

/**
 * @author Deividi
 * @since 11/11/2013
 */
public interface ClienteDelegate {

    Page<Cliente> findClientes (String query, int page, int limit);

    Cliente salvar (Cliente clienteVO);

    void deletar (String uid);

    Cliente atualizar (Cliente clienteVO, String uid);

    Cliente buscarPorUid (String uid);
}
