package br.com.lawyer.core.entity.enumerated;

public enum Permissao {

    USER("ROLE_USER"),
    MANAGER("ROLE_MANAGER"),
    ADMIN("ROLE_ADMIN"),
    LAWYER("ROLE_LAWYER");

    private String value;

    private Permissao (String value) {
        this.value = value;
    }

    public String getValue () {
        return this.value;
    }

}
