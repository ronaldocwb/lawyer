package br.com.lawyer.web.delegate;

import br.com.lawyer.core.entity.Advogado;
import br.com.lawyer.core.exception.BusinessException;
import org.springframework.data.domain.Page;

/**
 * @author Ronaldo
 * @since 10/10/2013
 */
public interface AdvogadoDelegate {
    Page<Advogado> findAdvogadoPorPagina (String query, int page, int limit);

    Advogado salvar (Advogado advogadoVO) throws BusinessException;

    void deletar (String uid);

    Advogado atualizar (Advogado advogadoVO, String uid);

    Advogado buscarPorUid (String uid);

    Advogado buscarAdvogadoPorPessoaUid (String pessoaUid);
}
