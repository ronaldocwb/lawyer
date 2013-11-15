package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Empresa;
import br.com.lawyer.core.repository.IEmpresaRepository;
import br.com.lawyer.core.service.IEmpresaService;
import br.com.lawyer.core.service.IPessoaService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 26/09/2013
 */
@Service
public class EmpresaService extends BaseService<String, Empresa, IEmpresaRepository> implements IEmpresaService {

    @Autowired
    private IPessoaService pessoaService;

    /**
     * Construtor
     *
     * @param repository - DAO que será utilizado referente a entidade manipulada
     */
    @Autowired
    public EmpresaService (IEmpresaRepository repository) {
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
        Page<Empresa> empresas;
        if (StringUtils.isNotBlank(query)) {
            empresas = getRepository().findByRazaoSocialContainingOrNomeFantasiaContaining(query, query, pageRequest);
        } else {
            empresas = getRepository().findAll(pageRequest);
        }
        return empresas;
    }

    @Override
    public void deletarEmpresa(String uid) {
        pessoaService.removerReferenciaDaEmpresa(uid);
        getRepository().delete(uid);
    }
}
