package br.com.lawyer.core.entity;

public enum Permissao {

    USER("USER"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN"),
    LAWYER("LAWYER");

    private final String value;

    private Permissao (String value) {
        this.value = value;
    }

    private String getValue() {
        return this.value;
    }

}
