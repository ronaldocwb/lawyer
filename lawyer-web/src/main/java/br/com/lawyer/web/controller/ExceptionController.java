package br.com.lawyer.web.controller;

import br.com.lawyer.web.exception.RestException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Retorna os erros HTTP padrões.
 * Se a chamada for de uma URL comum, retorna uma pagina de erro.
 * Se for uma chamada para URL de servicos REST, retorna um JSON.
 */
@Controller
@RequestMapping(value = "/errors")
public class ExceptionController {

    /***
     * Esse é o método que recebe as chamadas para os erros HTTP comuns da aplicação, como 404, 403, 500.... etc.
     * Redireciona para o serviço que retorna uma página de erro ou um {@link ResponseBody} JSON caso for um erro no contexto da API
     * @param request
     * @param response
     * @param status
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/redirect/{status}", method = RequestMethod.GET)
    public void errorRedirect(HttpServletRequest request, HttpServletResponse response, @PathVariable("status") String status) throws ServletException, IOException {

        String uri = (String)request.getAttribute(WebUtils.ERROR_REQUEST_URI_ATTRIBUTE);

        String forwardUri;
        if (StringUtils.contains(uri, "/api/")) {
            forwardUri = "/errors/json/" + status;
        } else if (status.equals("403")) {
            forwardUri = "/authentication/index";
        } else {
            forwardUri = "/errors/pages/" + status;
        }

        request.getRequestDispatcher(forwardUri).forward(request, response);
    }

    /**
     * Retona o JSON para exceções ocorridas no contexto da API.
     * @param status
     * @return {@link RestException}
     */
    @RequestMapping(value = "/json/{status}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<RestException> jsonError(@PathVariable("status") String status) {

        RestException exception = new RestException();
        exception.setCause(status);

        if (Integer.parseInt(status) == 401) {
            exception.setMessage("Acesso não autorizado");
            return new ResponseEntity<>(exception, HttpStatus.UNAUTHORIZED);
        }

        if (Integer.parseInt(status) == 403) {
            exception.setMessage("Acesso não autorizado");
            return new ResponseEntity<>(exception, HttpStatus.FORBIDDEN);
        }
        exception.setMessage("O servidor encontrou o seguinte erro na sua chamada:" + status);
        exception.setClazz(RestException.class.toString());

        HttpStatus http = HttpStatus.valueOf(Integer.valueOf(status));

        return new ResponseEntity<>(exception, http);
    }

    /**
     * Retona uma view (.html) para exceções ocorridas no contexto da aplicação.
     * @param status
     * @return {String} pagina
     */
    @RequestMapping(value = "/pages/{status}", method = RequestMethod.GET)
    public String pageError(@PathVariable("status") String status) {
        return "/errors/" + status;
    }

}
