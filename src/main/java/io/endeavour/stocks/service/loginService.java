package io.endeavour.stocks.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class loginService {
    private static Logger LOGGER = LoggerFactory.getLogger(loginService.class);

    private String baseUrl;
    private String username;
    private String password;
    private RestTemplate restTemplate;

    public loginService(
            @Value("${client.stock-calculations.url}")  String baseUrl,
            @Value("${client.login.username}") String username,
            @Value("${client.login.password}") String password) {
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
        this.restTemplate = new RestTemplate();

        this.restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(this.username,
                this.password));

    }

    public String getBearerToken(){
        String loginUrl = this.baseUrl+"/login";

        LOGGER.info("Calling login url {}", loginUrl);
        String token = this.restTemplate.postForObject(loginUrl, null, String.class);
        LOGGER.info("Got back token {} ", token);

        return token;
    }
}

