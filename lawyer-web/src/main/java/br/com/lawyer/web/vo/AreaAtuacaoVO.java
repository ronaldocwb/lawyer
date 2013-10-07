package br.com.lawyer.web.vo;

import javax.persistence.Entity;

import br.com.lawyer.core.entity.AreaAtuacao;
import br.com.lawyer.core.entity.Empresa;
import br.com.lawyer.web.base.BaseVO;

@Entity
public class AreaAtuacaoVO extends BaseVO<AreaAtuacao> {

    private static final long serialVersionUID = 5093446877731548419L;

    private String uid;
	private String nome;
	
	public AreaAtuacaoVO(AreaAtuacao areaAtuacao) {
        super(areaAtuacao);
    }

    public AreaAtuacaoVO () { }
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }
}
