package br.com.lawyer.core.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Properties;

/**
 * Carrega informações de um arquivo de propriedades.
 * @author Deividi
 * @since 17/09/2013
 * TODO mover para uma pasta externa algum dia!
 */
public class SystemProperties {

    private static final Properties PROPS = new Properties();

    private SystemProperties() {
    }

    static {
        if (PROPS.isEmpty()) {
            try {
                ClassPathResource resource = new ClassPathResource("system.properties");
                PROPS.load(resource.getInputStream());
                Assert.notNull(PROPS.get("algumaPropriedade"), "Voce deve especificar a propriedade algumaPropriedade");
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("system.properties nao encontrado");
            }
        }
    }

    public static String getProperty(String property) {
        return (String) PROPS.get(property);
    }

}
