package io.endeavour.stocks;

import io.endeavour.stocks.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Test
    public void whenLoginIsSuccessful(){
        String bearerToken = loginService.getBearerToken();

        assertNotNull(bearerToken,"Expecting bearerToken value");
    }
}
