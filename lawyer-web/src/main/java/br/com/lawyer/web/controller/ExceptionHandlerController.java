package br.com.lawyer.web.controller;

import br.com.lawyer.core.exception.BusinessException;
import br.com.lawyer.web.exception.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public @ResponseBody RestException handleException(Exception e) {
        RestException restException = new RestException();

        restException.setInfo(e.getLocalizedMessage());
        restException.setMessage(e.getMessage());

        e.printStackTrace();

        return restException;
    }

    /**
     *
     * Método que captura qualquer exceção do tipo {BusinessException} que os métodos dos controllers lançam.
     * @param {@link BusinessException}
     * @return JSON para a requisição.
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<RestException> handleBusinessException(BusinessException e) {

        RestException restException = new RestException();

        restException.setClazz(e.getClass().getSimpleName());
        restException.setCause(e.getCause().toString());
        restException.setInfo("Business Exception - " + e.getLocalizedMessage());
        restException.setMessage(e.getMessage());

        e.printStackTrace();

        return new ResponseEntity<>(restException, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    /**
     *
     * Método que captura exceções de autenticação.
     * @param {@link BadCredentialsException}
     * @return JSON para a requisição não autorizada, com {@link HttpStatus} <code>UNAUTHORIZED</code> - 401.
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<RestException> handleBadCredentialsException(BadCredentialsException e) {

        RestException restException = new RestException();

        restException.setClazz("BadCredentialsException");
        restException.setMessage("Usuário ou senha incorretos.");
        restException.setInfo("BadCredentialsException");

        e.printStackTrace();

        return new ResponseEntity<>(restException, HttpStatus.UNAUTHORIZED);

    }

    /**
     *
     * Método que captura exceções de autenticação.
     * @param {@link BadCredentialsException}
     * @return JSON para a requisição não autorizada, com {@link HttpStatus} <code>UNAUTHORIZED</code> - 401.
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<RestException> handleNullPointerException(NullPointerException e) {

        RestException restException = new RestException();

        restException.setClazz(e.getClass().getSimpleName());
        restException.setCause(e.getCause().toString());
        restException.setInfo("NullPointerException - " + e.getLocalizedMessage());
        restException.setMessage(e.getMessage());

        e.printStackTrace();

        return new ResponseEntity<>(restException, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<RestException> handleHttpMessageNotReadableException(HttpRequestMethodNotSupportedException e) {

        RestException restException = new RestException();

        restException.setClazz(e.getClass().getSimpleName());
        //restException.setCause(e.getCause().toString());
        restException.setInfo("HttpRequestMethodNotSupportedException Exception - " + e.getLocalizedMessage());
        restException.setMessage(e.getMessage());

        e.printStackTrace();

        return new ResponseEntity<>(restException, HttpStatus.NOT_ACCEPTABLE);

    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<RestException> handleThrowable(Throwable e) {

        RestException restException = new RestException();

        restException.setClazz(e.getClass().getSimpleName());
        restException.setCause(e.getCause().toString());
        restException.setInfo("Throwable Exception - " + e.getLocalizedMessage());
        restException.setMessage(e.getMessage());

        e.printStackTrace();

        return new ResponseEntity<>(restException, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RestException> handleUnrecognizedPropertyException(HttpMessageNotReadableException e) {

        RestException restException = new RestException();

        restException.setClazz(e.getClass().getSimpleName());
        restException.setCause(e.getCause().toString());
        restException.setInfo("A entidade não possue o campo informado.");
        restException.setMessage(e.getMessage());

        e.printStackTrace();

        return new ResponseEntity<>(restException, HttpStatus.NOT_ACCEPTABLE);

    }


}
