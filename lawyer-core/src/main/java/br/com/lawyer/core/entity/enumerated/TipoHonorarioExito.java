package br.com.lawyer.core.entity.enumerated;

public enum TipoHonorarioExito {

    VALOR_FIXO(0, "Valor Fixo"),
    PERCENTUAL(1, "Percentual"),
    MISTO(2, "Misto");

    private TipoHonorarioExito(Integer codigo, String nome) {

    }
}
