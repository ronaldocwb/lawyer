package br.com.lawyer.web.delegate;

import br.com.lawyer.core.entity.Pessoa;
import br.com.lawyer.core.exception.BusinessException;
import org.springframework.data.domain.Page;

/**
 * @author Deividi
 * @since 10/10/2013
 */
public interface PessoaDelegate {
    Page<Pessoa> findPessoaPorPagina (String query, int page, int limit);

    Pessoa salvar (Pessoa pessoaVO) throws BusinessException;

    void deletar (String uid) throws BusinessException;

    Pessoa atualizar (Pessoa pessoaVO, String uid) throws BusinessException;

    Pessoa buscarPorUid (String uid);
}
