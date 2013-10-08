package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.common.Telefone;
import br.com.lawyer.web.base.BaseVO;

/**
 * @author Deividi
 * @since 28/09/2013
 */
public class TelefoneVO extends BaseVO<Telefone> {

    private String numero;

    public TelefoneVO() {}

    public TelefoneVO(Telefone telefone) {
        super(telefone);
    }

    public String getNumero () {
        return numero;
    }

    public void setNumero (String numero) {
        this.numero = numero;
    }
}
