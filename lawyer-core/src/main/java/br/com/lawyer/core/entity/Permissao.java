package br.com.lawyer.core.entity;

public enum Permissao {

    USER("USER"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN"),
    FINANCE("FINANCE"),
    READ_ONLY("READ_ONLY"),
    LAWYER("LAWYER");

    private String value;

    private Permissao (String value) {
        this.value = value;
    }

    private String getValue() {
        return this.value;
    }

}
