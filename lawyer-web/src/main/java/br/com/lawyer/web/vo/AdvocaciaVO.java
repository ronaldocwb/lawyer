package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.Advocacia;
import br.com.lawyer.web.base.BaseVO;

/**
 * @author Deividi
 * @since 31/10/2013
 */
public class AdvocaciaVO extends BaseVO<Advocacia> {

	private static final long serialVersionUID = 1L;
	
	private String uid;
	private String nome;

    public AdvocaciaVO (Advocacia advocacia) {
        super(advocacia);
    }

    public AdvocaciaVO () { }
    
    public String getUid () {
        return uid;
    }

    public void setUid (String uid) {
        this.uid = uid;
    }

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }
}
