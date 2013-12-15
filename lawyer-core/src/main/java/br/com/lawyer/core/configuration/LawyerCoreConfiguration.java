package br.com.lawyer.core.configuration;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.LoadTimeWeavingConfigurer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.instrument.classloading.jboss.JBossLoadTimeWeaver;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Deividi
 * @since 07/11/2013
 */
@Configuration
@ComponentScan ({"br.com.lawyer.core", "br.com.lawyer.core.service", "br.com.lawyer.core.repository.impl"})
@EnableTransactionManagement
@EnableJpaRepositories (basePackages = "br.com.lawyer.core.repository")
@EnableLoadTimeWeaving
@PropertySource ("classpath:system.properties")
public class LawyerCoreConfiguration implements LoadTimeWeavingConfigurer {

    private static final String PROPERTY_ARCHIVE_AUTODETECTION = "hibernate.archive.autodetection";


    @Bean
    public DataSource dataSource () {
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
    public EntityManagerFactory entityManagerFactory () {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("br.com.lawyer.core.entity");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();
        factory.setPersistenceProviderClass(HibernatePersistence.class);

        Properties jpaProterties = new Properties();

        jpaProterties.put(AvailableSettings.DIALECT, org.hibernate.dialect.PostgresPlusDialect.class);
        jpaProterties.put(AvailableSettings.FORMAT_SQL, true);
//        jpaProterties.put(AvailableSettings.HBM2DDL_AUTO, "update");
        jpaProterties.put(PROPERTY_ARCHIVE_AUTODETECTION, "class, hbm");
        jpaProterties.put(AvailableSettings.JTA_PLATFORM, org.hibernate.transaction.TransactionManagerLookup.class);
        jpaProterties.put(AvailableSettings.SHOW_SQL, false);
        jpaProterties.put(AvailableSettings.STATEMENT_BATCH_SIZE, "20");
        jpaProterties.put(AvailableSettings.HBM2DDL_IMPORT_FILES, "import.sql");
        factory.setPersistenceXmlLocation("META-IND/persistence.xml");

        factory.setJpaProperties(jpaProterties);

        return factory.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManager () {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        txManager.setDataSource(dataSource());
        return txManager;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator () {
        return new HibernateExceptionTranslator();
    }

    @Override
    public LoadTimeWeaver getLoadTimeWeaver () {
        return new JBossLoadTimeWeaver();
    }
}
