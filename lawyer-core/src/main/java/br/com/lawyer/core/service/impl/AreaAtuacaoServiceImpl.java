package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.AreaAtuacao;
import br.com.lawyer.core.repository.AreaAtuacaoRepository;
import br.com.lawyer.core.repository.predicates.AreaAtuacaoPredicate;
import br.com.lawyer.core.service.AreaAtuacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @author Deividi
 * @since 26/09/2013
 */
@Service
public class AreaAtuacaoServiceImpl extends BaseServiceImpl<String, AreaAtuacao, AreaAtuacaoRepository> implements AreaAtuacaoService {

	private static final long serialVersionUID = 1L;

	/**
     * Construtor
     *
     * @param repository - DAO que sera utilizado referente a entidade manipulada
     */
    @Autowired
    public AreaAtuacaoServiceImpl (AreaAtuacaoRepository repository) {
        super(repository);
    }

    /**
     * Busca os resultados das empresas por paginacao informada.
     * Se o parametro <code>query</code> for informado, faz um <code>like</code> com {@link br.com.lawyer.core.entity.AreaAtuacao#nome}
     * Se o parametro <code>query</code> nao for informado, retorna todas.
     * @param query
     * @param pageRequest
     * @return Page<AreaAtuacao>
     */
    @Override
    public Page<AreaAtuacao> buscarPorNomeLike (String query, PageRequest pageRequest) {
        return getRepository().findAll(AreaAtuacaoPredicate.nomeIsLike(query), pageRequest);
    }
}
