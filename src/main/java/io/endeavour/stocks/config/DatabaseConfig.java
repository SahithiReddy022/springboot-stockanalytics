package io.endeavour.stocks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;

import javax.sql.DataSource;
//we are telling spring that we are creating beans in this class with @beans
@Configuration
public class DatabaseConfig {

    //dependency injection
//    @Autowired
//    private DataSource dataSource;

@Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
    return DataSourceBuilder.create().build();
}

@Bean
    @ConfigurationProperties(prefix = "spring.datasource-crudjpa")
    public DataSource crudDataSource(){
    return DataSourceBuilder.create().build();

}
}
