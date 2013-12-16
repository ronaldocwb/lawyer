package br.com.lawyer.web.delegate;

import br.com.lawyer.core.entity.Responsavel;
import br.com.lawyer.core.exception.BusinessException;
import org.springframework.data.domain.Page;

/**
 * @author Deividi
 * @since 22/10/2013
 */
public interface ResponsavelDelegate {
    Page<Responsavel> findResponsavelPorPagina (String query, int page, int limit) throws BusinessException;

    Responsavel salvar (Responsavel pessoaVO);

    void deletar (String uid);

    Responsavel atualizar (Responsavel pessoaVO, String uid);

    Responsavel buscarPorUid (String uid);

}
