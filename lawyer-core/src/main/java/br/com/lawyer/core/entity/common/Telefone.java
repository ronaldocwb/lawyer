package br.com.lawyer.core.entity.common;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Telefone implements Serializable {

    private static final long serialVersionUID = 8490402582329057874L;

    private String numero;

    public String getNumero () {
        return numero;
    }

    public void setNumero (String numero) {
        this.numero = numero;
    }
}
