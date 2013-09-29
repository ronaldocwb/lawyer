package br.com.lawyer.core.entity.common;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Telefone implements Serializable {

    private static final long serialVersionUID = 8490402582329057874L;

    public Telefone () {
    }

    public Telefone (Integer codPais, Integer codRegiao, Long numero) {
        this.codPais = codPais;
        this.codRegiao = codRegiao;
        this.numero = numero;
    }

    public Telefone (Integer codRegiao, Long numero) {
        this.codPais = 55;
        this.codRegiao = codRegiao;
        this.numero = numero;
    }

    Integer codPais;
    Integer codRegiao;
    Long numero;

    public Integer getCodPais () {
        return codPais;
    }

    public void setCodPais (Integer codPais) {
        this.codPais = codPais;
    }

    public Integer getCodRegiao () {
        return codRegiao;
    }

    public void setCodRegiao (Integer codRegiao) {
        this.codRegiao = codRegiao;
    }

    public Long getNumero () {
        return numero;
    }

    public void setNumero (Long numero) {
        this.numero = numero;
    }
}
