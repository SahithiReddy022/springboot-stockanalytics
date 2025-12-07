package io.endeavour.stocks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = {"io.endeavour.stocks.repository.crud"},
        entityManagerFactoryRef = "crudEntityManagerFactory",
        transactionManagerRef = "crudTransactionManager"
)
public class CrudDBConfig {
    @Autowired
    @Qualifier(value = "crudDataSource")
    private DataSource dataSource;

    /***
     * This method is used to configure JPA with the following steps:
     * 1) Create an EntityManagerFactoryBean and tie it to the dataSource.
     * 2) Direct the EntityManagerFactoryBean to the package where the entity classes are present
     * 3) Define an JPA implementation library that implements the specs, which is Hibernate in our case
     * 4) Provide the database vendor information to the JPA Implementation library, which is Postgres in our case.
     * 5) Set the JPA implementation vendor object into the EntityManagerFactoryBean
     * @return EntityManagerFactoryBean
     */
    @Bean(name = "crudEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        //1. Create an LocalContainerEntityManagerFactoryBean and give it the dataSource
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        //2. Specify the package to scan for Entities
        entityManagerFactoryBean.setPackagesToScan("io.endeavour.stocks.entity.crud");
        //3. Define an JPA provider, Hibernate in this case
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        //4. Provide the database vendor information to the JPA implementation library, which is POSTGRESQL
        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        hibernateJpaVendorAdapter.setShowSql(true);
        //5. Set the Hibernate Adapter object as the vendor adapter to Entity Manager
        entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);

        return entityManagerFactoryBean;
    }

    @Bean(name = "crudTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier(value = "crudEntityManagerFactory")
                                                         EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
