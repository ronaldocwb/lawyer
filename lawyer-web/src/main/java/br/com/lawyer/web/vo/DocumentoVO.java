package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.Documento;
import br.com.lawyer.web.base.BaseVO;

/**
 * @author Deividi
 * @since 28/09/2013
 */
public class DocumentoVO extends BaseVO<Documento> {

    public DocumentoVO (Documento documento) {
        super(documento);
    }

    public DocumentoVO () {

    }

}
