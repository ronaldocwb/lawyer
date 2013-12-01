package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.common.Email;
import br.com.lawyer.web.base.BaseVO;

/**
 * @author Deividi
 * @since 17/10/2013
 */
public class EmailVO extends BaseVO<Email> {

	private static final long serialVersionUID = 1L;
	
	private String value;
    private boolean principal;

    public EmailVO (Email email) {
        super(email);
    }

    public EmailVO(){
    }

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
