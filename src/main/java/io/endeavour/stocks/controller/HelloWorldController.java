package io.endeavour.stocks.Controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping(value = "/hello")
    public String sayHello(){
        return "Hello Java Class Oct 2025";
    }

    @GetMapping(value = "/hello-name/{personName}")
    public String sayHelloWithName(@PathVariable String personName){
        return "Hello " + personName + " Today is " + LocalDate.now();
    }
}
