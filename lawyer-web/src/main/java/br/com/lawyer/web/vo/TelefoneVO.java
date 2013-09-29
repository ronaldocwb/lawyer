package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.common.Telefone;
import br.com.lawyer.web.base.BaseVO;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author Deividi
 * @since 28/09/2013
 */
public class TelefoneVO extends BaseVO<Telefone> {

    private Integer codPais;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private Integer codRegiao;

    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private Long numero;

    public TelefoneVO() {}

    public TelefoneVO(Telefone telefone) {
        super(telefone);
    }

    public Integer getCodPais () {
        return codPais;
    }

    public void setCodPais (Integer codPais) {
        this.codPais = codPais;
    }

    public Integer getCodRegiao () {
        return codRegiao;
    }

    public void setCodRegiao (Integer codRegiao) {
        this.codRegiao = codRegiao;
    }

    public Long getNumero () {
        return numero;
    }

    public void setNumero (Long numero) {
        this.numero = numero;
    }
}
