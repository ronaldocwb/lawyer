package br.com.lawyer.web.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Interface que mapeia o contexto da api - "/api" para os controllers que forem utilizá-la.
 * Isso Simplifica as anotações em classes da API que não precisam declarar o path em todas elas.
 * Caso o contexto inicial da API seja alterado, somente essa interface precisa ser alterada.
 * @author Deividi Cavarzan
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Controller
@RequestMapping(value = "/api")
public @interface ApiController {
}
