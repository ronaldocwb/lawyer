package br.com.lawyer.core.entity.common;

import br.com.lawyer.core.base.IUID;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Pais implements Serializable, IUID<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @Column (name = "CD_PAIS", unique = true, nullable = false)
    private Long cdPais;

    @Column (name = "CD_PAIS_IBGE", length = 5)
    private String cdPaisIbge;

    @Column (name = "NM_PAIS", nullable = false, length = 40)
    private String nmPais;

    @Column (name = "SG_PAIS", nullable = false, length = 5)
    private String sgPais;

    @OneToMany (mappedBy = "pais", fetch = FetchType.LAZY)
    private List<Estado> estados;

    public Pais () {
    }

    @Override
    public Long getUid () {
        return getCdPais();
    }

    public Long getCdPais () {
        return this.cdPais;
    }

    public void setCdPais (Long cdPais) {
        this.cdPais = cdPais;
    }

    public String getCdPaisIbge () {
        return this.cdPaisIbge;
    }

    public void setCdPaisIbge (String cdPaisIbge) {
        this.cdPaisIbge = cdPaisIbge;
    }


    public String getNmPais () {
        return this.nmPais;
    }

    public void setNmPais (String nmPais) {
        this.nmPais = nmPais;
    }

    public String getSgPais () {
        return this.sgPais;
    }

    public void setSgPais (String sgPais) {
        this.sgPais = sgPais;
    }

    public List<Estado> getEstados () {
        return this.estados;
    }

    public void setEstados (List<Estado> estados) {
        this.estados = estados;
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((cdPais == null) ? 0 : cdPais.hashCode());
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
        Pais other = (Pais) obj;
        if (cdPais == null) {
            if (other.cdPais != null)
                return false;
        } else if (!cdPais.equals(other.cdPais))
            return false;
        return true;
    }
}