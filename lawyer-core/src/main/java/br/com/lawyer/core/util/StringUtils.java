package br.com.lawyer.core.util;

import java.util.Scanner;

/**
 * Implementação da StringUtils com metodos adicionais proprios.
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

    /**
     * @param values
     * @return true se o array passado como parâmetro contem alguma string nula ou vazia.
     */
    public static boolean containStringBlank (String... values) {
        boolean containsBlank = false;

        for (String value : values) {
            if (isBlank(value)) {
                containsBlank = true;
                break;
            }
        }
        return containsBlank;
    }
    
    @SuppressWarnings("resource")
	public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
