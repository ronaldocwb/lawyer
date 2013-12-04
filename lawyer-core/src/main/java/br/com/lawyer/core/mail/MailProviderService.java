package br.com.lawyer.core.mail;

import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import br.com.lawyer.core.util.mail.MailMessage;
import br.com.lawyer.core.util.mail.MailSender;

import com.google.common.io.Resources;

@Service
public class MailProviderService {
	
	private static final String TEMPLATE_DIR = "br/com/lawyer/core/mail/template/";
	
	//TODO verificar como o Spring substitui os eventos CDI
	//@Autowired
	//private Event<MailMessage> mailMessageEvent;
	
	public void enviarEmailCadastro(String nome, String emailLogin) {
		MailMessage m = new MailMessage();
		
		m.setDestinatario(nome, emailLogin);
		m.setAssunto("Bem vindo ao sistema Lawyer!");
		m.setMensagem(montaMensagemCadastro(nome, emailLogin));
		
		//mailMessageEvent.fire(m);
		MailSender.sendMail(m);
	}
    
	public String montaMensagemCadastro(String nome, String emailLogin){
		try {
			//Recupera o resource usando o Guava
			URL url = Resources.getResource(TEMPLATE_DIR + "mailCadastro.html");
			System.out.println(url);
			
			String html = IOUtils.toString(url);
			
			html = html.replace("#{nome}", nome);
			html = html.replace("#{email}", emailLogin);
			
			//TODO Colocar url para completar o cadastro
			html = html.replace("#{url}", "http://lawyer.com/cadastro/new/tokenxptoyz");
			
			return html;
		} catch (Exception e) {
			throw new RuntimeException("Não foi Possivel ler o arquivo de template para envio de email!");
		}
	} 
}
