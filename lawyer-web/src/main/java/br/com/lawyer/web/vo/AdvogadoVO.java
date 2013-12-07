package br.com.lawyer.web.vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import br.com.lawyer.core.entity.Advogado;
import br.com.lawyer.web.annotation.IgnoreVOParser;
import br.com.lawyer.web.base.BaseVO;

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
    
    @IgnoreVOParser
    private boolean geraUsuario;

	public AdvogadoVO(Advogado advogado) {
        super(advogado);
    }

    public AdvogadoVO () {

    }
    
    @Override
    public Advogado parse() {
    	if(isGeraUsuario()){
    		//TODO Avaliar se usa o primeiro ou cria um flag
    		if(getPessoa().getEmails() != null 
    				&& getPessoa().getEmails().size() > 0){
    		
    			getPessoa().setUsuario(
    					new UsuarioVO(
    							getPessoa().getEmails().get(0).getValue()));
    		}
    	}
    	
    	return super.parse();
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
	
    public boolean isGeraUsuario() {
		return geraUsuario;
	}

	public void setGeraUsuario(boolean geraUsuario) {
		this.geraUsuario = geraUsuario;
	}


}
