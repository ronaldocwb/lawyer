package br.com.lawyer.core.util.mail;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.internet.InternetAddress;

public class MailMessage implements Serializable {
	
private static final long serialVersionUID = 5574933126412233453L;
	
	private List<InternetAddress> destinatario;
	private List<InternetAddress> comCopia;
	private List<InternetAddress> comCopiaOculta;
	private InternetAddress remetente;
	private String assunto;
	private String mensagem;
	private String arquivoMensagem;
	
	/**
	 * @return o valor do atributo destinatario
	 * @author Ronaldo Campos de Oliveira
	 * @since 20/06/2008 
	 */
	public List<InternetAddress> getDestinatario() {
		return destinatario;
	}


	public void setDestinatario(String nome, String email) {
		try {
			setDestinatario(Arrays.asList(new InternetAddress(email, nome)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao setar remetente da mensagem de email");
		}
	}

	/**
	 * @param destinatario O valor de destinatario para atribuir no atributo
	 * @author Ronaldo Campos de Oliveira
	 * @since 20/06/2008 
	 */
	public void setDestinatario(List<InternetAddress> destinatario) {
		this.destinatario = destinatario;
	}


	/**
	 * @return o valor do atributo comCopia
	 * @author Ronaldo Campos de Oliveira
	 * @since 20/06/2008 
	 */
	public List<InternetAddress> getComCopia() {
		return comCopia;
	}


	/**
	 * @param comCopia O valor de comCopia para atribuir no atributo
	 * @author Ronaldo Campos de Oliveira
	 * @since 20/06/2008 
	 */
	public void setComCopia(List<InternetAddress> comCopia) {
		this.comCopia = comCopia;
	}


	/**
	 * @return o valor do atributo comCopiaOculta
	 * @author Ronaldo Campos de Oliveira
	 * @since 20/06/2008 
	 */
	public List<InternetAddress> getComCopiaOculta() {
		return comCopiaOculta;
	}


	/**
	 * @param comCopiaOculta O valor de comCopiaOculta para atribuir no atributo
	 * @author Ronaldo Campos de Oliveira
	 * @since 20/06/2008 
	 */
	public void setComCopiaOculta(List<InternetAddress> comCopiaOculta) {
		this.comCopiaOculta = comCopiaOculta;
	}


	/**
	 * @return o valor do atributo remetente
	 * @author Ronaldo Campos de Oliveira
	 * @since 20/06/2008 
	 */
	public InternetAddress getRemetente() {
		return remetente;
	}


	/**
	 * @param remetente O valor de remetente para atribuir no atributo
	 * @author Ronaldo Campos de Oliveira
	 * @since 20/06/2008 
	 */
	public void setRemetente(InternetAddress remetente) {
		this.remetente = remetente;
	}

	public void setRemetente(String nome, String email) {
		try {
			setRemetente(new InternetAddress(email, nome));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao setar remetente da mensagem de email");
		}
	}


	/**
	 * @return o valor do atributo assunto
	 * @author Ronaldo Campos de Oliveira
	 * @since 20/06/2008 
	 */
	public String getAssunto() {
		return assunto;
	}


	/**
	 * @param assunto O valor de assunto para atribuir no atributo
	 * @author Ronaldo Campos de Oliveira
	 * @since 20/06/2008 
	 */
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}


	/**
	 * @return o valor do atributo arquivoTemplate
	 * @author Ronaldo Campos de Oliveira
	 * @since 20/06/2008 
	 */
	//public String getArquivoTemplate() {
	//	return arquivoTemplate;
	//}


	/**
	 * @param arquivoTemplate O valor de arquivoTemplate para atribuir no atributo
	 * @author Ronaldo Campos de Oliveira
	 * @since 20/06/2008 
	 */
	//public void setArquivoTemplate(String arquivoTemplate) {
	//	this.arquivoTemplate = arquivoTemplate;
	//}


	/**
	 * @return o valor do atributo arquivoMensagem
	 * @author Ronaldo Campos de Oliveira
	 * @since 20/06/2008 
	 */
	public String getArquivoMensagem() {
		return arquivoMensagem;
	}


	/**
	 * @param arquivoMensagem O valor de arquivoMensagem para atribuir no atributo
	 * @author Ronaldo Campos de Oliveira
	 * @since 20/06/2008 
	 */
	public void setArquivoMensagem(String arquivoMensagem) {
		this.arquivoMensagem = arquivoMensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
	public void addDestinatario(String nome, String email){
		if(destinatario == null){
			destinatario = new ArrayList<InternetAddress>();
		}
		try {
			destinatario.add(new InternetAddress(email, nome));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao setar destinatario da mensagem de email");
		}
	}
	
	public void addComCopia(String nome, String email){
		if(comCopia == null){
			comCopia = new ArrayList<InternetAddress>();
		}
		try {
			comCopia.add(new InternetAddress(email, nome));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao setar com cópia da mensagem de email");
		}
	}
	
	public void addComCopiaOculta(String nome, String email){
		if(comCopiaOculta == null){
			comCopiaOculta = new ArrayList<InternetAddress>();
		}
		try {
			comCopiaOculta.add(new InternetAddress(email, nome));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("Falha ao setar com copia oculta da mensagem de email");
		}
	}
}
