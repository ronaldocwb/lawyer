package br.com.lawyer.core.exception;

public class ParseVOToEntityException extends RuntimeException {

    public ParseVOToEntityException (Exception e) {
    }

    public ParseVOToEntityException (String message, Exception e) {
        super(message, e);
    }

}
