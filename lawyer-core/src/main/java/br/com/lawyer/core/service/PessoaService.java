package br.com.lawyer.core.service;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Pessoa;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.PessoaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Deividi
 * @since 26/09/2013
 */
public interface PessoaService extends BaseService<String, Pessoa, PessoaRepository> {

    Page<Pessoa> buscarPorNomeLike(String query, PageRequest pageRequest);

    void removerReferenciaDaEmpresaPorUid (String uid);

    Pessoa salvar (Pessoa parse) throws BusinessException;

    Pessoa atualizar (Pessoa parse) throws BusinessException;

    void deletar (String uid) throws BusinessException;

    Pessoa salvarOuAtualizar (Pessoa pessoa);
}
