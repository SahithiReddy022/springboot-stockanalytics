package io.endeavour.stocks.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

//first controller
@RestController
public class HelloWorldController {

    @GetMapping(value = "/hello")
    public String sayHello(){
        return "Hello Java Class Oct 2025";
    }

    @GetMapping(value = "/hello-name/{personName}")
    public String sayHelloWithName(@PathVariable String personName,
                                   @RequestParam String city,
                                   @RequestParam String state
    ){
        return "Hello " + personName + " Today is " + LocalDate.now() + " city " + city + " state " + state;
    }

    @GetMapping(value = "/hello-date/{date}")
    public String sayHelloWithDate(@PathVariable @DateTimeFormat(pattern = "MM-dd-yyyy")
                                       LocalDate date){
        return "Hello you entered the date " + date;
    }

    @PostMapping(value="/sort-tickers")
    public List<String> sortTickers(@RequestBody List<String> tickers){
        Collections.sort(tickers);
        return tickers;
    }
}
