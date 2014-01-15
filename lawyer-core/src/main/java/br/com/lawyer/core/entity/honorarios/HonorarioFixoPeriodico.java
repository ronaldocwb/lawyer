package br.com.lawyer.core.entity.honorarios;


import br.com.lawyer.core.entity.ConvencaoHonorarios;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigInteger;

/**
 * Representa a convenção de honorário onde é pago um valor periodico definido em contrato
 * 
 * @author Ronaldo
 */
@Entity
@DiscriminatorValue("VALOR_FIXO_PERIODICO")
public class HonorarioFixoPeriodico extends ConvencaoHonorarios {

    private BigInteger valor;

    public BigInteger getValor() {
        return valor;
    }

    public void setValor(BigInteger valor) {
        this.valor = valor;
    }
}
