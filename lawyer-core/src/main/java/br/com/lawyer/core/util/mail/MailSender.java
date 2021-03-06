package br.com.lawyer.core.util.mail;

import br.com.lawyer.core.mail.MailEvent;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import java.io.Serializable;

@Component
public class MailSender implements ApplicationListener<MailEvent>, Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(MailSender.class);

    @Override
    public void onApplicationEvent (MailEvent mailEvent) {
        sendMail(mailEvent.getMailMessage());
    }

    public void sendMail (MailMessage email) {
        try {
            Session mailSession = InitialContext.doLookup("java:/LawyerMail");

            MimeMessage m = new MimeMessage(mailSession);
            InternetAddress from = new InternetAddress("lawyer@lawyer.com", "Lawyer");

            m.setFrom(from);
            m.setSubject(email.getAssunto());
            m.setSentDate(new java.util.Date());
            m.setContent(email.getMensagem(), "text/html");

            m.setRecipients(Message.RecipientType.TO, (Address[]) email.getDestinatario().toArray());
            Transport.send(m);
            logger.info("Email enviado com Sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("<font color=red>Error in Sending Mail: " + e + "</font>");
            throw new RuntimeException("Erro ao enviar email");
        }
    }
}
