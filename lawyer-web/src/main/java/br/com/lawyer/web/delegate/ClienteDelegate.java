package br.com.lawyer.web.delegate;

import br.com.lawyer.web.vo.ClienteVO;
import org.springframework.data.domain.Page;

/**
 * @author Deividi
 * @since 11/11/2013
 */
public interface ClienteDelegate {

    Page<ClienteVO> findClientes (String query, int page, int limit);

    ClienteVO salvar (ClienteVO clienteVO);

    void deletar (String uid);

    ClienteVO atualizar (ClienteVO clienteVO, String uid);

    ClienteVO buscarPorUid (String uid);
}
