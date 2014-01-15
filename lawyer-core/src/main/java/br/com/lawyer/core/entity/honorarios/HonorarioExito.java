package br.com.lawyer.core.entity.honorarios;


import br.com.lawyer.core.entity.ConvencaoHonorarios;
import br.com.lawyer.core.entity.enumerated.TipoHonorarioExito;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigInteger;

/**
 * Representa a convenção de honorário onde é pago um valor fixo
 * caso o processo tenha exito ou um percentual sobre os ganhos
 * 
 * @author Ronaldo
 */
@Entity
@DiscriminatorValue("EXITO")
public class HonorarioExito extends ConvencaoHonorarios {

    @Enumerated(EnumType.ORDINAL)
    private TipoHonorarioExito tpExito;

    private BigInteger valor;

    private BigInteger percentual;

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
    }

    public TipoHonorarioExito getTpExito() {
        return tpExito;
    }

    public void setTpExito(TipoHonorarioExito tpExito) {
        this.tpExito = tpExito;
    }

    public BigInteger getPercentual() {
        return percentual;
    }

    public void setPercentual(BigInteger percentual) {
        this.percentual = percentual;
    }
}
