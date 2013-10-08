package br.com.lawyer.core.entity.common;

import br.com.lawyer.core.base.IUID;

import javax.persistence.*;


@Entity
public class Municipio implements IUID<Long> {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "CD_MUNICIPIO", unique = true, nullable = false)
    private Long cdMunicipio;

    @Column (name = "CD_MUNICIPIO_IBGE", length = 7)
    private String cdMunicipioIbge;

    @Column (name = "NM_MUNICIPIO", nullable = false, length = 40)
    private String nmMunicipio;

    @ManyToOne
    @JoinColumn (name = "CD_ESTADO", nullable = false)
    private Estado estado;

    public Municipio () {
    }

    @Override
    public Long getUid () {
        return getCdMunicipio();
    }

    public Long getCdMunicipio () {
        return this.cdMunicipio;
    }

    public void setCdMunicipio (Long cdMunicipio) {
        this.cdMunicipio = cdMunicipio;
    }

    public String getCdMunicipioIbge () {
        return this.cdMunicipioIbge;
    }

    public void setCdMunicipioIbge (String cdMunicipioIbge) {
        this.cdMunicipioIbge = cdMunicipioIbge;
    }

    public String getNmMunicipio () {
        return this.nmMunicipio;
    }

    public void setNmMunicipio (String nmMunicipio) {
        this.nmMunicipio = nmMunicipio;
    }

    public Estado getEstado () {
        return this.estado;
    }

    public void setEstado (Estado estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((cdMunicipio == null) ? 0 : cdMunicipio.hashCode());
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
        Municipio other = (Municipio) obj;
        if (cdMunicipio == null) {
            if (other.cdMunicipio != null)
                return false;
        } else if (!cdMunicipio.equals(other.cdMunicipio))
            return false;
        return true;
    }

}