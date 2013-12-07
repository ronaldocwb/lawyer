package br.com.lawyer.core.service.impl;

import static br.com.lawyer.core.repository.predicates.AdvogadoPredicate.buscaSePossuirNome;
import static br.com.lawyer.core.repository.predicates.AdvogadoPredicate.possuiPessoaUid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.Advogado;
import br.com.lawyer.core.entity.Pessoa;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.mail.MailProviderService;
import br.com.lawyer.core.repository.AdvogadoRepository;
import br.com.lawyer.core.service.AdvogadoService;
import br.com.lawyer.core.service.PessoaService;
import br.com.lawyer.core.service.UsuarioService;
import br.com.lawyer.core.util.StringUtils;

/**
 * @author Deividi
 * @since 26/09/2013
 */
@Service
public class AdvogadoServiceImpl extends BaseServiceImpl<String, Advogado, AdvogadoRepository> implements AdvogadoService {
   	
	private static final long serialVersionUID = -6152185939231872977L;

    private static final Logger logger = Logger.getLogger(AdvogadoService.class);

    @Autowired
    private PessoaService pessoaService;
    
    @Autowired
    private MailProviderService mailProviderService;
    
    @Autowired
    private UsuarioService usuarioService;
    
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
     * Salva umAdvogado nome. Caso a pessoa ja exista, faz o merge da pessoa antes de salvar.
     * @param advogado
     * @return Advogado
     */
    @Override
    public Advogado salvarAdvogado (Advogado advogado) throws BusinessException {
    	logger.info(String.format("Salvando o advogado de UID %s pelo usuário %s", advogado.getNumeroOAB(), getUsuarioLogado().getEmail()));
        if (advogado.getPessoa() != null) {
        	Pessoa pessoa;
        	if(StringUtils.isNotBlank(advogado.getPessoa().getUid())){
        		pessoa = pessoaService.atualizar(advogado.getPessoa());
        	}else{
        		pessoa = pessoaService.salvar(advogado.getPessoa());
        	}
            advogado.setPessoa(pessoa);
        }
        Advogado retorno =  getRepository().save(advogado);
        logger.info(String.format("Advogado de UID %s foi salva pelo usuário %s", advogado.getUid(), getUsuarioLogado().getEmail()));
        
        if(advogado.hasUsuario()){
        	//TODO injetar a advocacia do usuario logado e setar no usuario que está sendo criado
            advogado.getPessoa().associaUsuario();
            usuarioService.salvar(advogado.getPessoa().getUsuario());
        	
        	mailProviderService.enviarEmailCadastro(advogado.getNome(), advogado.getEmailLogin());
        }
        
        return retorno;
    }

	/**
     * Busca um advogado pelo nome da pessoa. Se n�o for um nome, retorna null.
     * @param pessoaUid
     * @return Advogado
     */
    @Override
    public Advogado buscarAdvogadoPorPessoaUid (String pessoaUid) {
        return getRepository().findOne(possuiPessoaUid(pessoaUid));
    }

    @Override
    public void removerPorPessoaUid (String uid) throws BusinessException {
        logger.info(String.format("Apagando advogado pelo UID %s da pessoa pelo usu�rio %s", uid, getUsuarioLogado().getEmail()));
        getRepository().deletarPorPessoaUid(uid);
        logger.info(String.format("Advogado pelo UID %s da pessoa apagado pelo usu�rio %s", uid, getUsuarioLogado().getEmail()));
    }
}
