package br.com.lawyer.web.delegate;

import br.com.lawyer.web.vo.AtividadeVO;
import org.springframework.data.domain.Page;

/**
 * @author Deividi
 * @since 11/11/2013
 */
public interface AtividadeDelegate {
    Page<AtividadeVO> findAtividadePorPagina (String query, int page, int limit);

    AtividadeVO salvar (AtividadeVO advogadoVO);

    void deletar (String uid);

    AtividadeVO atualizar (AtividadeVO advogadoVO, String uid);

    AtividadeVO buscarPorUid (String uid);
}
