package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.Empresa;
import br.com.lawyer.core.repository.EmpresaRepository;
import br.com.lawyer.core.repository.predicates.EmpresaPredicate;
import br.com.lawyer.core.service.EmpresaService;
import br.com.lawyer.core.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 26/09/2013
 */
@Service
public class EmpresaServiceImpl extends BaseServiceImpl<String, Empresa, EmpresaRepository> implements EmpresaService {

    @Autowired
    private PessoaService pessoaService;

    /**
     * Construtor
     *
     * @param repository - DAO que será utilizado referente a entidade manipulada
     */
    @Autowired
    public EmpresaServiceImpl (EmpresaRepository repository) {
        super(repository);
    }

    /**
     * Busca os resultados das empresas por paginação informada.
     * Se o parametro <code>query</code> for informado, faz um <code>like</code> com {@link Empresa#razaoSocial} ou {@link Empresa#nomeFantasia}
     * Se o parametro <code>query</code> nao for informado, retorna todas.
     *
     * @param query
     * @param pageRequest
     * @return Page<Empresa>
     */
    @Override
    public Page<Empresa> buscarPorRazaoSocialOuNomeFantasiaLike (String query, PageRequest pageRequest) {
        return getRepository().findAll(EmpresaPredicate.nomeIsLike(query), pageRequest);
    }

    @Override
    public void deletarEmpresa(String uid) {
        pessoaService.removerReferenciaDaEmpresa(uid);
        getRepository().delete(uid);
    }
}
