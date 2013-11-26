package br.com.lawyer.core.entity.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Deividi
 * @since 13/10/2013
 */
@Embeddable
public class Email implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String value;
    private boolean principal;
    
    @Column(name="email")
	public String getValue () {
        return value;
    }

    public void setValue (String value) {
        this.value = value;
    }
    
    public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}
}
