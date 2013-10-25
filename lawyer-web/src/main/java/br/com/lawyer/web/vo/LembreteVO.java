package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.Lembrete;
import br.com.lawyer.web.base.BaseVO;

/**
 * @author Deividi
 * @since 22/10/2013
 */
public class LembreteVO extends BaseVO<Lembrete> {

    private String uid;

    private String texto;

    private boolean finalizado;

    public LembreteVO(Lembrete lembrete) {
        super(lembrete);
    }

    public LembreteVO() {
        super();
    }

    public String getTexto () {
        return texto;
    }

    public void setTexto (String texto) {
        this.texto = texto;
    }

    public boolean isFinalizado () {
        return finalizado;
    }

    public void setFinalizado (boolean finalizado) {
        this.finalizado = finalizado;
    }

    public String getUid () {
        return uid;
    }

    public void setUid (String uid) {
        this.uid = uid;
    }
}
