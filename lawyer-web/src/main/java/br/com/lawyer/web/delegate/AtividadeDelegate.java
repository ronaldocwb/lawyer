package br.com.lawyer.web.delegate;

import br.com.lawyer.core.entity.Atividade;
import org.springframework.data.domain.Page;

/**
 * @author Deividi
 * @since 11/11/2013
 */
public interface AtividadeDelegate {
    Page<Atividade> findAtividadePorPagina (String query, int page, int limit);

    Atividade salvar (Atividade advogadoVO);

    void deletar (String uid);

    Atividade atualizar (Atividade advogadoVO, String uid);

    Atividade buscarPorUid (String uid);
}
