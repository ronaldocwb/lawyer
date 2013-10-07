package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.common.Estado;
import br.com.lawyer.web.base.BaseVO;

/**
 * @author Deividi
 * @since 07/10/2013
 */
public class EstadoVO extends BaseVO<Estado> {

    private String cdEstadoIbge;

    private String nmEstado;

    public EstadoVO (Estado estado) {
        super(estado);
    }

    public EstadoVO () {    }

    public String getCdEstadoIbge () {
        return cdEstadoIbge;
    }

    public void setCdEstadoIbge (String cdEstadoIbge) {
        this.cdEstadoIbge = cdEstadoIbge;
    }

    public String getNmEstado () {
        return nmEstado;
    }

    public void setNmEstado (String nmEstado) {
        this.nmEstado = nmEstado;
    }
}
