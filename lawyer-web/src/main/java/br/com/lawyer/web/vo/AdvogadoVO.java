package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.Advogado;
import br.com.lawyer.web.base.BaseVO;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Ronaldo
 * @since 22/10/2013
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize (include = JsonSerialize.Inclusion.NON_NULL)
public class AdvogadoVO extends BaseVO<Advogado>{

	private static final long serialVersionUID = -722346124086136322L;

	private String uid;

    private PessoaVO pessoa;
    
    private String numeroOAB;

    public AdvogadoVO(Advogado advogado) {
        super(advogado);
    }

    public AdvogadoVO () {

    }

    public String getUid () {
        return uid;
    }

    public void setUid (String uid) {
        this.uid = uid;
    }

	public PessoaVO getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaVO pessoa) {
		this.pessoa = pessoa;
	}

	public String getNumeroOAB() {
		return numeroOAB;
	}

	public void setNumeroOAB(String numeroOAB) {
		this.numeroOAB = numeroOAB;
	}

}
