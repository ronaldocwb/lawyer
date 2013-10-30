package br.com.lawyer.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Deividi
 * @since 30/10/2013
 */
@Target ({ElementType.FIELD})
@Retention (RetentionPolicy.RUNTIME)
public @interface IgnoreVOParser {
}
