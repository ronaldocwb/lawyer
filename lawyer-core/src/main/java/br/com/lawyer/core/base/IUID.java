package br.com.lawyer.core.base;

import java.io.Serializable;

/**
 * Descreve o contrato de uma entidade que possui uma chave (IUID)
 *
 * @author Deividi Cavarzan
 *
 */
public interface IUID<T extends Serializable> {

    /**
     * Retorna a chave primaria da entidade.
     *
     * @return a chave primaria da entidade.
     */
    public T getUid();

}
