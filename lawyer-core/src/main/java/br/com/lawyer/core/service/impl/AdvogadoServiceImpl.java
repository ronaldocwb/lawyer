package br.com.lawyer.core.service.impl;

import static br.com.lawyer.core.repository.predicates.AdvogadoPredicate.buscaSePossuirNome;
import static br.com.lawyer.core.repository.predicates.AdvogadoPredicate.possuiPessoaUid;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.lawyer.core.base.BaseServiceImpl;
import br.com.lawyer.core.entity.Advogado;
import br.com.lawyer.core.entity.Pessoa;
import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.core.mail.MailMessage;
import br.com.lawyer.core.mail.MailSender;
import br.com.lawyer.core.repository.AdvogadoRepository;
import br.com.lawyer.core.service.AdvogadoService;
import br.com.lawyer.core.service.PessoaService;
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
    private MailSender mailSender;
    
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
        if (advogado.getPessoa() != null && StringUtils.isNotBlank(advogado.getPessoa().getUid())) {
            Pessoa pessoa = pessoaService.save(advogado.getPessoa());
            advogado.setPessoa(pessoa);
        }
        logger.info(String.format("Advogado de UID %s foi salva pelo usuário %s", advogado.getUid(), getUsuarioLogado().getEmail()));
        Advogado retorno =  getRepository().save(advogado);
        
        if(advogado.hasUsuario()){
        	enviarEmailCadastro(advogado.getNome(), advogado.getEmailLogin());
        }
        
        return retorno;
    }

    private void enviarEmailCadastro(String nome, String emailLogin) {
		MailMessage m = new MailMessage();
		
		m.setDestinatario(nome, emailLogin);
		m.setAssunto("Bem vindo ao sistema Lawyer!");
		m.setMensagem(montaMensagem(nome, emailLogin));
		
		mailSender.sendMail(m);
	}
    
    private String montaMensagem(String nome, String emailLogin){
		try {
			InputStream fileStream = 
					Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("br/com/lawyer/core/mail/template/mailCadastro.html");
			
			String html = StringUtils.convertStreamToString(fileStream);
			
			html = html.replace("#{nome}", nome);
			html = html.replace("#{email}", emailLogin);
			
			//TODO Colocar url para completar o cadastro
			html = html.replace("#{url}", "http://lawyer.com/cadastro/new/tokenxptoyz");
			
			return html;
		} catch (Exception e) {
			throw new RuntimeException("Não foi Possivel ler o arquivo de template para envio de email!");
		}
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
