package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.Atividade;
import br.com.lawyer.core.repository.AtividadeRepository;
import br.com.lawyer.core.repository.predicates.AtividadePredicate;
import br.com.lawyer.core.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 26/09/2013
 */
@Service
public class AtividadeServiceImpl extends BaseServiceImpl<String, Atividade, AtividadeRepository> implements AtividadeService {

	private static final long serialVersionUID = -6152185939231872977L;

	/**
     * Construtor
     *
     * @param repository - DAO que sera utilizado referente a entidade manipulada
     */
    @Autowired
    public AtividadeServiceImpl (AtividadeRepository repository) {
        super(repository);
    }

    /**
     * Busca os resultados das empresas por paginacao informada.
     * Se o parametro <code>query</code> for informado, faz um <code>like</code> com {@link br.com.lawyer.core.entity.Atividade}
     * Se o parametro <code>query</code> nao for informado, retorna todas.
     * @param query
     * @param pageRequest
     * @return Page<Atividade>
     */
    @Override
    public Page<Atividade> buscarPorNomeLike (String query, PageRequest pageRequest) {
        return getRepository().findAll(AtividadePredicate.possuiNomeAssuntoLike(query), pageRequest);
    }
}
