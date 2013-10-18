package br.com.lawyer.core.entity.common;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @author Deividi
 * @since 13/10/2013
 */
@Embeddable
public class Email implements Serializable{

    private String value;

    public String getValue () {
        return value;
    }

    public void setValue (String value) {
        this.value = value;
    }
}
