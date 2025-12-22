package io.endeavour.stocks.service;

import io.endeavour.stocks.Controller.StocksController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {
    private static Logger LOGGER = LoggerFactory.getLogger(StocksController.class);

    private String baseUrl;
    private String userName;
    private String password;
    private RestTemplate restTemplate;

    public LoginService(
            @Value("${client.stock-calculations.url}") String baseUrl,
            @Value("${client.login.username}") String userName,
            @Value("${client.login.password}") String password) {
        this.baseUrl = baseUrl;
        this.userName = userName;
        this.password = password;
        this.restTemplate = new RestTemplate();

        this.restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(this.userName,
                this.password));
    }

    public String getBearerToken(){
        String  loginUrl = this.baseUrl+"/login";

        LOGGER.info("Calling login url {}",loginUrl);
        String token = this.restTemplate.postForObject(loginUrl, null, String.class);
        LOGGER.info("Got token back{}",token);

        return token;


    }
}
