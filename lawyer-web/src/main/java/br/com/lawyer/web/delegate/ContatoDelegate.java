package br.com.lawyer.web.delegate;

import br.com.lawyer.core.entity.Contato;
import org.springframework.data.domain.Page;

/**
 * @author Deividi
 * @since 11/11/2013
 */
public interface ContatoDelegate {

    Page<Contato> findContatos (String query, String tipo, int page, int limit);

    Contato salvar (Contato contato);

    void deletar (String uid);

    Contato atualizar (Contato contato, String uid);

    Contato buscarPorUid (String uid);

    Page<Contato> findContatosEmpresas (String q, int page, int limit);

    Page<Contato> findContatosPessoas (String q, int page, int limit);

    Page<Contato> findContatosUsuarios (String q, int page, int limit);
}
