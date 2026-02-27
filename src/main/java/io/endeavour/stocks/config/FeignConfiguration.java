package io.endeavour.stocks.config;
import feign.RequestInterceptor;
import io.endeavour.stocks.service.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class FeignConfiguration {

    @Autowired
    private loginService loginService;
    public FeignConfiguration(loginService loginService) {
        this.loginService = loginService;
    }
    @Bean
    public RequestInterceptor getRequestInterceptor() {
        return template -> template.header("Authorization", "Bearer " + loginService.getBearerToken());
    }
}
