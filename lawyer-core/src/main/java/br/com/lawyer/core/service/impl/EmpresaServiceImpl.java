package br.com.lawyer.core.service.impl;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.Contato;
import br.com.lawyer.core.entity.Empresa;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.repository.EmpresaRepository;
import br.com.lawyer.core.repository.predicates.EmpresaPredicate;
import br.com.lawyer.core.service.ContatoService;
import br.com.lawyer.core.service.EmpresaService;
import br.com.lawyer.core.service.PessoaService;
import org.apache.log4j.Logger;
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

    private static final Logger logger = Logger.getLogger(EmpresaService.class);

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ContatoService contatoService;

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
    public void deletarEmpresa (String uid) throws BusinessException {
        logger.info(String.format("Apagando a empresa de UID %s pelo usuário %s", uid, getUsuarioLogado().getEmail()));

        pessoaService.removerReferenciaDaEmpresaPorUid(uid);
        contatoService.removerPorReferenciaUid(uid, Empresa.class);
        getRepository().delete(uid);

        logger.info(String.format("Empresa de UID %s foi apagada pelo usuário %s", uid, getUsuarioLogado().getEmail()));
    }

    @Override
    public Empresa salvar (Empresa empresa) throws BusinessException {
        logger.info(String.format("Salvando a empresa de UID %s pelo usuário %s", empresa.getNomeFantasia(), getUsuarioLogado().getEmail()));

        saveAndFlush(empresa);
        Contato contato = new Contato(empresa);
        contatoService.save(contato);

        logger.info(String.format("Empresa de UID %s foi salva pelo usuário %s", empresa.getUid(), getUsuarioLogado().getEmail()));
        return empresa;
    }

    @Override
    public Empresa atualizar (Empresa empresa) throws BusinessException {
        logger.info(String.format("Atualizando a empresa de UID %s pelo usuário %s", empresa.getNomeFantasia(), getUsuarioLogado().getEmail()));
        Empresa stored = findOne(empresa.getUid());
        if (stored != null && !stored.getCliente().equals(empresa.getCliente())) {
            contatoService.removerPorReferenciaUid(stored.getUid(), Empresa.class);
        }
        save(empresa);

        logger.info(String.format("Empresa de UID %s foi atualizada pelo usuário %s", empresa.getUid(), getUsuarioLogado().getEmail()));
        return empresa;
    }
}
