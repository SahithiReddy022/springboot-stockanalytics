package io.endeavour.stocks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class StocksJDBCJPAConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(dataSource);
    }

    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate(dataSource);
    }

    /***
     * This method is used to configure JPA with the following steps:
     * 1) Create an EntityManagerFactoryBean and tie it to the dataSource.
     * 2) Direct the EntityManagerFactoryBean to the package where the entity classes are present
     * 3) Define an JPA implementation library that implements the specs, which is Hibernate in our case
     * 4) Provide the database vendor information to the JPA Implementation library, which is Postgres in our case.
     * 5) Set the JPA implementation vendor object into the EntityManagerFactoryBean
     * @return EntityManagerFactoryBean
     */
    @Bean(name = "entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        //1. Create an LocalContainerEntityManagerFactoryBean and give it the dataSource
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        //2. Specify the package to scan for Entities
        emf.setPackagesToScan("io.endeavour.stocks.entity.stocks");
        //3. Define an JPA provider, Hibernate in this case
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        //4. Provide the database vendor information to the JPA implementation library, which is POSTGRESQL
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setShowSql(true);
        //5. Set the Hibernate Adapter object as the vendor adapter to Entity Manager
        emf.setJpaVendorAdapter(vendorAdapter);

        return emf;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier(value = "entityManagerFactory")
                                                         EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }
}