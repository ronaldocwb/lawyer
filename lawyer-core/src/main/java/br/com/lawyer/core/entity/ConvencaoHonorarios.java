package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;

import javax.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(columnDefinition="tpConvencaoHonorarios")
public class ConvencaoHonorarios extends AbstractBaseEntity {

    @Column (length = 120)
    private String nome;

    //@Enumerated
    //private TipoConvencaoHonorarios tpConvencaoHonorarios;

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    //public TipoConvencaoHonorarios getTpConvencaoHonorarios () {
    //    return tpConvencaoHonorarios;
    //}

    //public void setTpConvencaoHonorarios (
    //        TipoConvencaoHonorarios tpConvencaoHonorarios) {
    //    this.tpConvencaoHonorarios = tpConvencaoHonorarios;
    //}

}