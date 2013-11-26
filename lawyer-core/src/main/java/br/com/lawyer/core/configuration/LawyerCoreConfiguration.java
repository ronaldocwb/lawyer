package br.com.lawyer.core.configuration;

import org.hibernate.ejb.HibernatePersistence;
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
@EnableJpaRepositories(basePackages = "br.com.lawyer.core.repository")
@EnableLoadTimeWeaving
public class LawyerCoreConfiguration implements LoadTimeWeavingConfigurer {

    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROPERTY_NAME_LOOKUP_CLASS = "hibernate.transaction.manager_lookup_class";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String PROPERTY_ARCHIVE_AUTODETECTION = "hibernate.archive.autodetection";
    private static final String PROPERTY_BATCH_SIZE = "hibernate.jdbc.batch_size";
    private static final String PROPERTY_IMPORT_FILE = "hibernate.hbm2ddl.import_files";


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
        factory.setPersistenceProviderClass(HibernatePersistence.class);

        Properties jpaProterties = new Properties();

        jpaProterties.put(PROPERTY_NAME_HIBERNATE_DIALECT, org.hibernate.dialect.PostgresPlusDialect.class);
        jpaProterties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, true);
        jpaProterties.put(PROPERTY_NAME_HIBERNATE_HBM2DDL_AUTO, "create-drop");
        jpaProterties.put(PROPERTY_ARCHIVE_AUTODETECTION, "class, hbm");
        jpaProterties.put(PROPERTY_NAME_LOOKUP_CLASS, org.hibernate.transaction.TransactionManagerLookup.class);
        jpaProterties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, true);
        jpaProterties.put(PROPERTY_BATCH_SIZE, "20");
        jpaProterties.put(PROPERTY_IMPORT_FILE, "import.sql");

        factory.setJpaProperties(jpaProterties);


        return factory.getObject();
    }

    @Bean
    public JpaTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        txManager.setDataSource(dataSource());
        return txManager;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator(){
        return new HibernateExceptionTranslator();
    }

//    @Bean
//    public EntityManager entityManger() {
//        return entityManagerFactory().createEntityManager();
//    }

    @Override
    public LoadTimeWeaver getLoadTimeWeaver () {
        return new org.springframework.instrument.classloading.jboss.JBossLoadTimeWeaver();
    }
}
