package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Atividade;
import br.com.lawyer.core.repository.IAtividadeRepository;
import br.com.lawyer.core.service.IAtividadeService;
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
public class AtividadeService extends BaseService<String, Atividade, IAtividadeRepository> implements IAtividadeService {

	private static final long serialVersionUID = -6152185939231872977L;

	/**
     * Construtor
     *
     * @param repository - DAO que sera utilizado referente a entidade manipulada
     */
    @Autowired
    public AtividadeService (IAtividadeRepository repository) {
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
        Page<Atividade> atividades = null;
        if (StringUtils.isNotBlank(query)) {
//            atividades = getRepository().findByNameContaining(query, pageRequest);
        } else {
            atividades = getRepository().findAll(pageRequest);
        }
        return atividades;
    }
}
