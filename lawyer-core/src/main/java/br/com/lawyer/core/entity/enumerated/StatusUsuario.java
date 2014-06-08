package br.com.lawyer.core.entity.enumerated;

public enum StatusUsuario {

    SEM_ATIVACAO_INICIAL(0, "Sem Ativaçao Inicial"),
    ATIVO(1, "Ativo"),
    INATIVO(2, "Inativo");

    private StatusUsuario (Integer codigo, String nome) {

    }
}
