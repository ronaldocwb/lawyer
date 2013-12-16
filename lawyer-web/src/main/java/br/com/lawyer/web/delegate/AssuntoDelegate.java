package br.com.lawyer.web.delegate;

import br.com.lawyer.core.entity.Assunto;
import br.com.lawyer.core.exception.BusinessException;
import org.springframework.data.domain.Page;

/**
 * @author Deividi
 * @since 15/11/2013
 */
public interface AssuntoDelegate {
    Page<Assunto> findAssuntoPorNomeOuPagina (String nome, int page, int limit);

    Assunto salvar (Assunto assunto);

    void deletar (String uid);

    Assunto update (Assunto assunto, String uid);

    Assunto findOne (String uid);

    Page<Assunto> findAssuntosPorClienteUid (String uid, int page, int limit) throws BusinessException;
}
