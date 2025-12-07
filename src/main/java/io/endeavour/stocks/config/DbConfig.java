package io.endeavour.stocks.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbConfig {
    @Bean(name = "stocksDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getStocksDatasource(){
      return DataSourceBuilder.create().build();
    }

    @Bean(name="crudDataSource")
    @ConfigurationProperties(prefix = "spring.datasource-crudjpa")
    public DataSource getCrudDatasource(){
        return DataSourceBuilder.create().build();
    }
}
