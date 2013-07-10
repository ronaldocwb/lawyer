package br.com.lawyer.web.controller;

import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.exception.RestException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Deividi Cavarzan
 * Classe responsável por capturar qualquer exception lançada pelos controller e convertê-las em uma resposta JSON amigável.
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public @ResponseBody RestException handleException(Exception e) {
        RestException restException = new RestException();

        restException.setClazz(e.getClass().getSimpleName());
        restException.setCause(e.getCause().toString());
        restException.setInfo(e.getLocalizedMessage());
        restException.setMessage(e.getMessage());

        return restException;

    }

    @ExceptionHandler(BusinessException.class)
    public @ResponseBody RestException handleBusinessException(Exception e) {

        RestException restException = new RestException();

        restException.setClazz(e.getClass().getSimpleName());
        restException.setCause(e.getCause().toString());
        restException.setInfo("Business Exception - " + e.getLocalizedMessage());
        restException.setMessage(e.getMessage());

        return restException;

    }
}
