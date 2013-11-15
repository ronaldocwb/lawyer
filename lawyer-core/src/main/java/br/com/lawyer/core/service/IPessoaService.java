package br.com.lawyer.core.service;

import br.com.lawyer.core.base.IBaseService;
import br.com.lawyer.core.entity.Pessoa;
import br.com.lawyer.core.repository.IPessoaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author Deividi
 * @since 26/09/2013
 */
public interface IPessoaService extends IBaseService<String, Pessoa, IPessoaRepository> {

    Page<Pessoa> buscarPorNomeLike(String query, PageRequest pageRequest);

    void removerReferenciaDaEmpresa (String uid);
}
