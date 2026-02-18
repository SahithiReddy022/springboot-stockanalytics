package io.endeavour.stocks.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

//first controller
@RestController
public class HelloWorldController {

    @GetMapping(value="/hello")
    public String sayHello(){
        return "hello Java Class Oct 2025";
}

    @GetMapping(value = "/hello-name/{personName}")
    public String sayHelloWithName(@PathVariable String personName,
                                   @RequestParam String city,  //means in url directly we can put sahithi?city=dallas&state=texas
                                   @RequestParam String state){ //http://localhost:8080/hello-name/Sahithi?city=Dallas&state=Texas
        return "Hello "+ personName+ " Today is "+LocalDate.now()+ " city "+city+ " state "+state;
    }

    @PostMapping("/tickers")
    public List<String > sortList (@RequestBody ArrayList<String> tickers){
        Collections.sort(tickers);
        return tickers;
    }
    }
