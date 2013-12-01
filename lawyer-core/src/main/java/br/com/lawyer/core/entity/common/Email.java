package br.com.lawyer.core.entity.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Deividi
 * @since 13/10/2013
 */
@Embeddable
public class Email implements Serializable{

	private static final long serialVersionUID = 1L;

    @Column
	private String value;

    @Column
    private Boolean principal;
    
    @Column(name="email")
	public String getValue () {
        return value;
    }

    public void setValue (String value) {
        this.value = value;
    }


    public Boolean getPrincipal () {
        return principal;
    }

    public void setPrincipal (Boolean principal) {
        this.principal = principal;
    }
}
