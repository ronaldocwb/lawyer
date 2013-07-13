package br.com.lawyer.web.exception;

/**
 * Objeto de exceção que é lançada para as requisições REST.
 * @author Deividi
 */
public class RestException {

    private String clazz;

    private String cause;

    private String message;

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
