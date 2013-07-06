package br.com.lawyer.web.util;

import org.apache.commons.lang.StringUtils;

/**
 * Implementação da StringUtils com metodos proprios
 */
public class LawyerStringUtils extends StringUtils {

    /**
     *
     * @param values
     * @return true se o array passado como parâmetro contem alguma string nula ou vazia.
     */
    public boolean containStringBlank(String... values) {
        boolean containsBlank = false;

        for (String value : values) {
            if (isBlank(value)) {
                containsBlank = true;
                break;
            }
        }
        return containsBlank;
    }
}
