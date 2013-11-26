package br.com.lawyer.core.entity.enumerated;

public enum StatusUsuario {

    SEM_ATIVACAO_INICIAL(0, "Público"),
    ATIVO(1, "Privado"),
    INATIVO(2, "Grupo");

    private StatusUsuario (Integer codigo, String nome) {

    }
}
