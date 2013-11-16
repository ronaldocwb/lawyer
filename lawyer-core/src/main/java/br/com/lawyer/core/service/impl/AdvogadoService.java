package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseService;
import br.com.lawyer.core.entity.Advogado;
import br.com.lawyer.core.entity.Pessoa;
import br.com.lawyer.core.repository.IAdvogadoRepository;
import br.com.lawyer.core.repository.predicates.AdvogadoPredicate;
import br.com.lawyer.core.service.IAdvogadoService;
import br.com.lawyer.core.service.IPessoaService;
import br.com.lawyer.core.util.LawyerStringUtils;
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
public class AdvogadoService extends BaseService<String, Advogado, IAdvogadoRepository> implements IAdvogadoService {
   	
	private static final long serialVersionUID = -6152185939231872977L;

    @Autowired
    private IPessoaService pessoaService;

	/**
     * Construtor
     *
     * @param repository - DAO que sera utilizado referente a entidade manipulada
     */
    @Autowired
    public AdvogadoService (IAdvogadoRepository repository) {
        super(repository);
    }

    /**
     * Busca os resultados das empresas por paginacao informada.
     * Se o parametro <code>query</code> for informado, faz um <code>like</code> com {@link br.com.lawyer.core.entity.Advogado#pessoa#nome}
     * Se o parametro <code>query</code> nao for informado, retorna todas.
     * @param query
     * @param pageRequest
     * @return Page<Advogado>
     */
    @Override
    public Page<Advogado> buscarPorNomeLike (String query, PageRequest pageRequest) {
        Page<Advogado> advogados;
        if (StringUtils.isNotBlank(query)) {
            advogados = getRepository().findByNameContaining(query, pageRequest);
        } else {
            advogados = getRepository().findAll(pageRequest);
        }
        return advogados;
    }

    @Override
    public Advogado salvarAdvogado (Advogado advogado) {
        if (advogado.getPessoa() != null && LawyerStringUtils.isNotBlank(advogado.getPessoa().getUid())) {
            Pessoa pessoa = pessoaService.save(advogado.getPessoa());
            advogado.setPessoa(pessoa);
        }
        return getRepository().save(advogado);

    }

    @Override
    public Advogado buscarAdvogadoPorPessoaUid (String pessoaUid) {
        return getRepository().findOne(AdvogadoPredicate.possuiPessoaUid(pessoaUid));
    }
}
