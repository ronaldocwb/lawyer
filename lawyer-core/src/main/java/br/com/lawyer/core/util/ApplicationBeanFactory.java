package br.com.lawyer.core.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Deividi
 * @since 17/09/2013
 */
public class ApplicationBeanFactory {

    /** Arquivo de configuração dos beans no contexto do JPA. */
    private static final String JPA_FILE_PATH = "spring-context-core.xml";

    private static ApplicationContext jpaContext;

    /**
     * Efetua o lookup do bean usando o spring-context-core.
     *
     * @param clazz classe do bean.
     * @return instância do bean.
     * @see org.springframework.beans.factory.BeanFactory#getBean(Class)
     */
    public static <T> T getBean(Class<T> clazz) {
        if (jpaContext == null) {
            jpaContext = new ClassPathXmlApplicationContext(JPA_FILE_PATH);
        }

        return jpaContext.getBean(clazz);
    }

    /**
     * Efetua o lookup do bean usando o spring-context-core.
     *
     * @param clazz String do bean.
     * @return instância do bean.
     * @see org.springframework.beans.factory.BeanFactory#getBean(Class)
     */
    public static <T> T getBeanByName(String clazz) {
        if (jpaContext == null) {
            jpaContext = new ClassPathXmlApplicationContext(JPA_FILE_PATH);
        }

        return (T) jpaContext.getBean(clazz);
    }

}
