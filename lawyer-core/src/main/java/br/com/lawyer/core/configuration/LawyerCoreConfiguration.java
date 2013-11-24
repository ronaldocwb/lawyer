package br.com.lawyer.core.configuration;

import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author Deividi
 * @since 07/11/2013
 */
@Configuration
@ComponentScan ({"br.com.lawyer.core", "br.com.lawyer.core.service", "br.com.lawyer.core.repository.impl"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "br.com.lawyer.core.repository")
@EnableLoadTimeWeaving
public class LawyerCoreConfiguration implements LoadTimeWeavingConfigurer {

    @Bean
    public DataSource dataSource()  {
        DataSource dataSource = null;
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:jboss/postgresDS");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("br.com.lawyer.core.entity");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator(){
        return new HibernateExceptionTranslator();
    }

    @Bean
    public EntityManager entityManger() {
        return entityManagerFactory().createEntityManager();
    }

    @Override
    public LoadTimeWeaver getLoadTimeWeaver () {
        return new org.springframework.instrument.classloading.jboss.JBossLoadTimeWeaver();
    }
}
