package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.common.Municipio;
import br.com.lawyer.web.base.BaseVO;

/**
 * @author Deividi
 * @since 07/10/2013
 */
public class MunicipioVO extends BaseVO<Municipio> {

    private Long cdMunicipio;

    private String nmMunicipio;

    private EstadoVO estado;

    public MunicipioVO (Municipio municipio) {
        super(municipio);
    }

    public MunicipioVO () {    }

    public MunicipioVO (String s) {
        System.out.println(s);
    }

    public Long getCdMunicipio () {
        return cdMunicipio;
    }

    public void setCdMunicipio (Long cdMunicipio) {
        this.cdMunicipio = cdMunicipio;
    }

    public String getNmMunicipio () {
        return nmMunicipio;
    }

    public void setNmMunicipio (String nmMunicipio) {
        this.nmMunicipio = nmMunicipio;
    }

    public EstadoVO getEstado () {
        return estado;
    }

    public void setEstado (EstadoVO estado) {
        this.estado = estado;
    }
}
