package br.com.lawyer.web.delegate;

import br.com.lawyer.core.entity.Setor;
import org.springframework.data.domain.Page;

/**
 * @author Deividi
 * @since 03/11/2013
 */
public interface SetorDelegate {
    Page<Setor> findAllByNome (String query, int page, int limit);

    Setor salvar (Setor setorVO);

}
