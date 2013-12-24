package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.Pessoa;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.PessoaRepository;
import br.com.lawyer.core.repository.predicates.PessoaPredicate;
import br.com.lawyer.core.service.AdvogadoService;
import br.com.lawyer.core.service.ContatoService;
import br.com.lawyer.core.service.PessoaService;
import br.com.lawyer.core.util.StringUtils;
import org.apache.log4j.Logger;
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
public class PessoaServiceImpl extends BaseServiceImpl<String, Pessoa, PessoaRepository> implements PessoaService {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(PessoaService.class);

    @Autowired
    private ContatoService contatoService;

    @Autowired
    private AdvogadoService advogadoService;

    @Autowired
    public PessoaServiceImpl (PessoaRepository repository) {
        super(repository);
    }

    /**
     * Busca os resultados das empresas por paginacao informada.
     * Se o parametro <code>query</code> for informado, faz um <code>like</code> com {@link br.com.lawyer.core.entity.Pessoa#nome}
     * Se o parametro <code>query</code> nao for informado, retorna todas.
     *
     * @param query
     * @param pageRequest
     * @return Page<Pessoa>
     */
    @Override
    public Page<Pessoa> buscarPorNomeLike (String query, PageRequest pageRequest) {
        return getRepository().findAll(PessoaPredicate.nomeIsLike(query), pageRequest);
    }

    /**
     * Remove as referencias de uma empresa com UID informado para poder apagar essa empresa.
     * As pessoas permanecem na base mas como orf�s
     * Precisa de um contexto transacional que fa�a uma opera��o de commit na transa��o pra fazer efeito.
     *
     * @param uid
     */
    @Override
    public void removerReferenciaDaEmpresaPorUid (String uid) {
        List<Pessoa> pessoas = getRepository().findByEmpresaUid(uid);
        for (Pessoa pessoa : pessoas) {
            pessoa.setEmpresa(null);
        }
    }

    /**
     * Salva uma {@link br.com.lawyer.core.entity.Pessoa}
     * Insere um {@link br.com.lawyer.core.entity.Contato} se essa pessoa estiver marcada como cliente.
     *
     * @param pessoa
     * @return {@link br.com.lawyer.core.entity.Pessoa}
     */
    @Override
    public Pessoa salvar (Pessoa pessoa) throws BusinessException {
        logger.info(String.format("Salvando a pessoa %s pelo usuario %s", pessoa.getNome(), getUsuarioLogado().getEmail()));
        saveAndFlush(pessoa);
        logger.info(String.format("Pessoa de UID %s salva pelo usuario %s", pessoa.getUid(), getUsuarioLogado().getEmail()));
        return pessoa;
    }

    /**
     * Atualiza uma {@link br.com.lawyer.core.entity.Pessoa}
     * Atualiza ou insere um {@link br.com.lawyer.core.entity.Contato} se essa pessoa estiver marcada como cliente.
     *
     * @param pessoa
     * @return {@link br.com.lawyer.core.entity.Pessoa}
     * @throws BusinessException
     */
    @Override
    public Pessoa atualizar (Pessoa pessoa) throws BusinessException {
        logger.info(String.format("Atualizando a pessoa de UID %s pelo usuario %s", pessoa.getUid(), getUsuarioLogado().getEmail()));
        save(pessoa);
        logger.info(String.format("Pessoa de UID %s atualizada pelo usuario %s", pessoa.getUid(), getUsuarioLogado().getEmail()));
        return pessoa;
    }

    @Override
    public void deletar (String uid) throws BusinessException {
        logger.info(String.format("Deletando a pessoa de UID %s pelo usuario %s", uid, getUsuarioLogado().getEmail()));
        contatoService.removerPorReferenciaUid(uid, Pessoa.class);
        advogadoService.removerPorPessoaUid(uid);
        delete(uid);
        logger.info(String.format("Pessoa de UID %s apagada usuario %s", uid, getUsuarioLogado().getEmail()));
    }

    @Override
    public Pessoa salvarOuAtualizar (Pessoa pessoa) {
        try {
            if (StringUtils.isBlank(pessoa.getUid())) {
                return this.salvar(pessoa);
            } else {
                return this.atualizar(pessoa);
            }
        } catch (BusinessException e) {
            logger.error("Erro ao salvar / atualizar pessoa: ", e);
            return null;
        }
    }
}
