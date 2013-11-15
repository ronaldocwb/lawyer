package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.Assunto;
import br.com.lawyer.web.base.BaseVO;

/**
 * @author Deividi
 * @since 15/11/2013
 */
public class AssuntoVO extends BaseVO<Assunto> {

    private String numeroProcesso;

    public AssuntoVO() {}

    public AssuntoVO(Assunto assunto) {
        super(assunto);
    }

    public String getNumeroProcesso () {
        return numeroProcesso;
    }

    public void setNumeroProcesso (String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }
}
