package br.com.lawyer.web.delegate;

import br.com.lawyer.core.entity.AreaAtuacao;
import org.springframework.data.domain.Page;

/**
 * @author Ronaldo
 * @since 26/09/2013
 */
public interface AreaAtuacaoDelegate {
    Page<AreaAtuacao> findAreaAtuacaoPorPagina (String q, int page, int limit);

    AreaAtuacao salvar (AreaAtuacao areaAtuacao);

    void deletar (String uid);

    AreaAtuacao atualizar (AreaAtuacao areaAtuacao, String uid);

    AreaAtuacao buscarPorUid (String uid);
}
