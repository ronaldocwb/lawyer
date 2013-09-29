package br.com.lawyer.web.vo;

import br.com.lawyer.core.entity.Setor;
import br.com.lawyer.web.base.BaseVO;

/**
 * @author Deividi
 * @since 28/09/2013
 */
public class SetorVO extends BaseVO<Setor> {

    private String uid;

    private String nome;

    public SetorVO (Setor setor) {
        super(setor);
    }

    public SetorVO () {

    }

    public String getUid () {
        return uid;
    }

    public void setUid (String uid) {
        this.uid = uid;
    }

    public String getNome () {
        return nome;
    }

    public void setNome (String nome) {
        this.nome = nome;
    }
}
