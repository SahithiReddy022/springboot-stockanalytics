package io.endeavour.stocks.Controller;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World! KeerthyPT";
    }

    @GetMapping("/greet/{name}")
    public String greetUser(@PathVariable String name) {
        return "Heil, " + name + "!! at "+LocalDate.now();
    }
}
