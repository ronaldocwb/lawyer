package br.com.lawyer.core.mail;

import br.com.lawyer.core.util.mail.MailMessage;
import com.google.common.io.Resources;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.concurrent.Executors;

@Service
public class MailProviderService {
	
	private static final String TEMPLATE_DIR = "br/com/lawyer/core/mail/template/";
	
	@Autowired
	private SimpleApplicationEventMulticaster eventMulticaster;

	public void enviarEmailCadastro(String nome, String emailLogin) {
		MailMessage m = new MailMessage();
		
		m.setDestinatario(nome, emailLogin);
		m.setAssunto("Bem vindo ao sistema Lawyer!");
		m.setMensagem(montaMensagemCadastro(nome, emailLogin));
		
		eventMulticaster.setTaskExecutor(Executors.newCachedThreadPool());
		eventMulticaster.multicastEvent(new MailEvent(this, m));
		
		//appContext.publishEvent(new MailEvent(this, m));
		//MailSender.sendMail(m);
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
