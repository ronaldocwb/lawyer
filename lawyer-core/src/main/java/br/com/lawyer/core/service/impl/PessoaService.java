package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Pessoa;
import br.com.lawyer.core.repository.IPessoaRepository;
import br.com.lawyer.core.repository.predicates.PessoaPredicate;
import br.com.lawyer.core.service.IPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Deividi
 * @since 26/09/2013
 */
@Service
public class PessoaService extends BaseService<String, Pessoa, IPessoaRepository> implements IPessoaService {
    /**
     * Construtor
     *
     * @param repository - DAO que será utilizado referente a entidade manipulada
     */
    @Autowired
    public PessoaService (IPessoaRepository repository) {
        super(repository);
    }

    /**
     * Busca os resultados das empresas por paginação informada.
     * Se o parametro <code>query</code> for informado, faz um <code>like</code> com {@link br.com.lawyer.core.entity.Pessoa#nome}
     * Se o parametro <code>query</code> nao for informado, retorna todas.
     * @param query
     * @param pageRequest
     * @return Page<Pessoa>
     */
    @Override
    public Page<Pessoa> buscarPorNomeLike (String query, PageRequest pageRequest) {
        return getRepository().findAll(PessoaPredicate.nomeIsLike(query), pageRequest);
    }

    @Override
    public void removerReferenciaDaEmpresa (String uid) {
        List<Pessoa> pessoas = getRepository().findByEmpresaUid(uid);

        for (Pessoa pessoa : pessoas) {
            pessoa.setEmpresa(null);
        }

    }
}
