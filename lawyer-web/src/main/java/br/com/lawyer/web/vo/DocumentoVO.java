package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.Documento;
import br.com.lawyer.core.entity.enumerated.TipoDocumento;
import br.com.lawyer.web.base.BaseVO;

/**
 * @author Deividi
 * @since 28/09/2013
 */
public class DocumentoVO extends BaseVO<Documento> {

    private String identificacao;

    private TipoDocumento tipoIdentificacao;

    public DocumentoVO (Documento documento) {
        super(documento);
    }

    public DocumentoVO () {

    }

    public DocumentoVO (String documento) {
        this.identificacao = documento;
    }

    public String getIdentificacao () {
        return identificacao;
    }

    public void setIdentificacao (String identificacao) {
        this.identificacao = identificacao;
    }

    public TipoDocumento getTipoIdentificacao () {
        return tipoIdentificacao;
    }

    public void setTipoIdentificacao (TipoDocumento tipoIdentificacao) {
        this.tipoIdentificacao = tipoIdentificacao;
    }
}
