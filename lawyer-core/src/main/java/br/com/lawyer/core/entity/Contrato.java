package br.com.lawyer.core.entity;

import br.com.lawyer.core.entity.base.AbstractBaseEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Contrato extends AbstractBaseEntity {

    @Column (length = 200)
    private String nome;

    @ManyToOne
    private Empresa empresa;

    @OneToMany
    @LazyCollection (LazyCollectionOption.FALSE)
    private List<ConvencaoHonorarios> convencaoHonorarios;

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }

    public Empresa getEmpresa () {
        return empresa;
    }

    public void setEmpresa (Empresa empresa) {
        this.empresa = empresa;
    }

    public List<ConvencaoHonorarios> getConvencaoHonorarios () {
        return convencaoHonorarios;
    }

    public void addConvencaoHonorarios (ConvencaoHonorarios convencaoHonorarios) {
        if (this.convencaoHonorarios == null) {
            this.convencaoHonorarios = new ArrayList<ConvencaoHonorarios>();
        }

        this.convencaoHonorarios.add(convencaoHonorarios);
    }

    public void removeConvencaoHonorarios (ConvencaoHonorarios convencaoHonorarios) {
        if (this.convencaoHonorarios == null) {
            return;
        }

        this.convencaoHonorarios.remove(convencaoHonorarios);
    }
}