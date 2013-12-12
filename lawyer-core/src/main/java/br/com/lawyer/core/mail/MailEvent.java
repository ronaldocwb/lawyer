package br.com.lawyer.core.mail;

import br.com.lawyer.core.util.mail.MailMessage;
import org.springframework.context.ApplicationEvent;

public class MailEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private MailMessage mailMessage;
	
	public MailEvent(Object source) {
		super(source);
	}

	public MailEvent(Object source, MailMessage mailMessage) {
		super(source);
		this.mailMessage = mailMessage;
	}

	public MailMessage getMailMessage() {
		return mailMessage;
	}

	public void setMailMessage(MailMessage mailMessage) {
		this.mailMessage = mailMessage;
	}

}
