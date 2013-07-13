package br.com.lawyer.web.controller;

import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.exception.RestException;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Deividi Cavarzan
 * Classe responsável por capturar qualquer exception lançada pelos controller e convertê-las em uma resposta JSON amigável.
 */
@ControllerAdvice
public class ExceptionHandlerController {

    /**
     *
     * Método que captura qualquer exceção do tipo {Exception} que os métodos dos controllers lançam.
     * @param {Exception}
     * @return JSON para a requisição.
     */
    @ExceptionHandler(Exception.class)
    public @ResponseBody RestException handleException(Exception e) {
        RestException restException = new RestException();

        restException.setClazz(e.getClass().getSimpleName());
        restException.setCause(e.getCause().toString());
        restException.setInfo(e.getLocalizedMessage());
        restException.setMessage(e.getMessage());

        return restException;

    }

    /**
     *
     * Método que captura qualquer exceção do tipo {BusinessException} que os métodos dos controllers lançam.
     * @param {@link BusinessException}
     * @return JSON para a requisição.
     */
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
