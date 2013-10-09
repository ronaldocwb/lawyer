package br.com.lawyer.core.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.AreaAtuacao;
import br.com.lawyer.core.repository.IAreaAtuacaoRepository;
import br.com.lawyer.core.service.IAreaAtuacaoService;

/**
 * @author Deividi
 * @since 26/09/2013
 */
@Service
public class AreaAtuacaoService extends BaseService<String, AreaAtuacao, IAreaAtuacaoRepository> implements IAreaAtuacaoService {

	private static final long serialVersionUID = 1L;

	/**
     * Construtor
     *
     * @param repository - DAO que sera utilizado referente a entidade manipulada
     */
    @Autowired
    public AreaAtuacaoService (IAreaAtuacaoRepository repository) {
        super(repository);
    }

    /**
     * Busca os resultados das empresas por paginacao informada.
     * Se o parametro <code>query</code> for informado, faz um <code>like</code> com {@link AreaAtuacao#razaoSocial} ou {@link AreaAtuacao#nomeFantasia}
     * Se o parametro <code>query</code> nao for informado, retorna todas.
     * @param query
     * @param pageRequest
     * @return Page<AreaAtuacao>
     */
    @Override
    public Page<AreaAtuacao> buscarPorNomeLike (String query, PageRequest pageRequest) {
        Page<AreaAtuacao> empresas;
        if (StringUtils.isNotBlank(query)) {
            empresas = getRepository().findByNomeLike(query, pageRequest);
        } else {
            empresas = getRepository().findAll(pageRequest);
        }
        return empresas;
    }
}