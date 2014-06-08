package br.com.lawyer.core.entity.honorarios;


import br.com.lawyer.core.entity.ConvencaoHonorarios;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Representa a convenção de honorário onde é pago pelo valor convencionado do 
 * advogado que executou a tarefa
 * 
 * @author Ronaldo
 */
@Entity
@DiscriminatorValue("VALOR_HORA_ADV")
public class HonorarioHoraAdvogado extends ConvencaoHonorarios {



}
