package io.endeavour.stocks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
//we are telling spring that we are creating beans in this class with @beans
@Configuration
public class DatabaseConfig {

    //dependency injection
    @Autowired
    private DataSource dataSource;



}
