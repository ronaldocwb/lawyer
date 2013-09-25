package br.com.lawyer.core.entity.enumerated;

import javax.persistence.Embeddable;

@Embeddable
public enum Permissao {

    USER("USER"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN"),
    LAWYER("LAWYER");

    private final String value;

    private Permissao (String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
