package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.Advogado;
import br.com.lawyer.core.entity.Pessoa;
import br.com.lawyer.core.repository.AdvogadoRepository;
import br.com.lawyer.core.service.AdvogadoService;
import br.com.lawyer.core.service.PessoaService;
import br.com.lawyer.core.util.LawyerStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static br.com.lawyer.core.repository.predicates.AdvogadoPredicate.buscaSePossuirNome;
import static br.com.lawyer.core.repository.predicates.AdvogadoPredicate.possuiPessoaUid;

/**
 * @author Deividi
 * @since 26/09/2013
 */
@Service
public class AdvogadoServiceImpl extends BaseServiceImpl<String, Advogado, AdvogadoRepository> implements AdvogadoService {
   	
	private static final long serialVersionUID = -6152185939231872977L;

    @Autowired
    private PessoaService pessoaService;

	/**
     * Construtor
     *
     * @param repository - DAO que sera utilizado referente a entidade manipulada
     */
    @Autowired
    public AdvogadoServiceImpl (AdvogadoRepository repository) {
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
        return getRepository().findAll(buscaSePossuirNome(query), pageRequest);
    }

    /**
     * Salva umAdvogado nome. Caso a pessoa já exista, faz o merge da pessoa antes de salvar.
     * @param advogado
     * @return Advogado
     */
    @Override
    public Advogado salvarAdvogado (Advogado advogado) {
        if (advogado.getPessoa() != null && LawyerStringUtils.isNotBlank(advogado.getPessoa().getUid())) {
            Pessoa pessoa = pessoaService.save(advogado.getPessoa());
            advogado.setPessoa(pessoa);
        }
        return getRepository().save(advogado);

    }

    /**
     * Busca um advogado pelo nome da pessoa. Se não for um nome, retorna null.
     * @param pessoaUid
     * @return Advogado
     */
    @Override
    public Advogado buscarAdvogadoPorPessoaUid (String pessoaUid) {
        return getRepository().findOne(possuiPessoaUid(pessoaUid));
    }
}
