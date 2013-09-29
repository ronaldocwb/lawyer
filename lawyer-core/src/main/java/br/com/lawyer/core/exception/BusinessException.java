package br.com.lawyer.core.exception;

/**
 * Exceção que o core lança para metodos da API ou demais projetos.
 * Ela pode ser repassada para o {@link org.springframework.web.bind.annotation.ControllerAdvice}
 */
public class BusinessException extends Exception {

    public BusinessException (Exception e) {
        super(e);
    }

    public BusinessException (String message) {
        super(message);
    }

}
