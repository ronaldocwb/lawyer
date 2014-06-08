package br.com.lawyer.core.entity.common;

import br.com.lawyer.core.base.IUID;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties (ignoreUnknown = true)
public class Estado implements IUID<Long> {

    @Id
    private Long cdEstado;

    @Column (name = "CD_ESTADO_IBGE", length = 2)
    private String cdEstadoIbge;

    @Column (name = "NM_ESTADO", nullable = false, length = 40)
    private String nmEstado;

    @Column (name = "SG_ESTADO", nullable = false, length = 4)
    private String sgEstado;

    @ManyToOne
    @JoinColumn (name = "CD_PAIS", nullable = false)
    private Pais pais;

    @OneToMany (mappedBy = "estado")
    @JsonIgnore
    private List<Municipio> municipios;

    public Estado () {
    }

    @Override
    public Long getUid () {
        return getCdEstado();
    }

    public Long getCdEstado () {
        return this.cdEstado;
    }

    public void setCdEstado (Long cdEstado) {
        this.cdEstado = cdEstado;
    }

    public String getCdEstadoIbge () {
        return this.cdEstadoIbge;
    }

    public void setCdEstadoIbge (String cdEstadoIbge) {
        this.cdEstadoIbge = cdEstadoIbge;
    }

    public String getNmEstado () {
        return this.nmEstado;
    }

    public void setNmEstado (String nmEstado) {
        this.nmEstado = nmEstado;
    }

    public String getSgEstado () {
        return this.sgEstado;
    }

    public void setSgEstado (String sgEstado) {
        this.sgEstado = sgEstado;
    }

    public Pais getPais () {
        return this.pais;
    }

    public void setPais (Pais pais) {
        this.pais = pais;
    }

    public List<Municipio> getMunicipios () {
        return this.municipios;
    }

    public void setMunicipios (List<Municipio> municipios) {
        this.municipios = municipios;
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((cdEstado == null) ? 0 : cdEstado.hashCode());
        return result;
    }

    @Override
    public boolean equals (Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Estado other = (Estado) obj;
        if (cdEstado == null) {
            if (other.cdEstado != null)
                return false;
        } else if (!cdEstado.equals(other.cdEstado))
            return false;
        return true;
    }

}