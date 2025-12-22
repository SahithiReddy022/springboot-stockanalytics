package io.endeavour.stocks.config;

import feign.RequestInterceptor;
import io.endeavour.stocks.service.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
    private final LoginService loginService;

    public FeignConfiguration(LoginService loginService) {
        this.loginService = loginService;
    }

    @Bean
    public RequestInterceptor getRequestInterceptor() {
        return template -> template.header("Authorization", "Bearer " + loginService.getBearerToken());
    }
}

