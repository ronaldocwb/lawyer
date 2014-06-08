package br.com.lawyer.core.entity.honorarios;


import br.com.lawyer.core.entity.ConvencaoHonorarios;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigInteger;

/**
 * Representa a convenção de honorário onde é pago pelo valor convencionado do 
 * advogado que executou a tarefa
 * 
 * @author Ronaldo
 */
@Entity
@DiscriminatorValue("VALOR_HORA_FIXA")
public class HonorarioHoraFixa extends ConvencaoHonorarios {

    private BigInteger valorHora;

    public BigInteger getValorHora() {
        return valorHora;
    }

    public void setValorHora(BigInteger valorHora) {
        this.valorHora = valorHora;
    }
}
