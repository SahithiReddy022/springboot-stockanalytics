package io.endeavour.stocks.controller;

import io.endeavour.stocks.exception.StockNotFoundException;
import io.endeavour.stocks.request.StockPriceHistoryRequest;
import io.endeavour.stocks.service.MarketAnalyticService;
import io.endeavour.stocks.vo.StockPriceHistoryVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
public class StocksController {

   private static Logger LOGGER = LoggerFactory.getLogger(StocksController.class);

    @Autowired
    private MarketAnalyticService marketAnalyticService;

    @GetMapping(value = "/dummy-stock-price")
    public StockPriceHistoryVO getDummyStockPriceHistoryVO(){
        return marketAnalyticService.getDummyStockPriceHistoryVO();
    }

    @GetMapping(value = "/single-stock-price/{ticker}")
    public List<StockPriceHistoryVO> geStockPriceHistoryVOList(@PathVariable String ticker){
        return marketAnalyticService.getStockPriceHistoryVOList(ticker);
    }

    @PostMapping(value = "/stock-price-history")
    public List<StockPriceHistoryVO> getStockPriceHistoryForTickers(@RequestBody
                                                                        StockPriceHistoryRequest stockPriceHistoryRequest){

        LocalDate fromDate = stockPriceHistoryRequest.getFromDate();
        LocalDate toDate = stockPriceHistoryRequest.getToDate();

        if(fromDate == null || toDate == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "fromDate and toDate required" );
        }

        if(fromDate.isAfter(toDate)){
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "fromDate should be before toDate");
        }

        LOGGER.info("Processing request for tickers {}, fromDate {} and toDate {}  ",
                stockPriceHistoryRequest.getTickers(), stockPriceHistoryRequest.getFromDate(),
                stockPriceHistoryRequest.getToDate());

        return marketAnalyticService.getStockPriceHistoryForTickersForDateRange(
                stockPriceHistoryRequest.getTickers(),
                fromDate,
                toDate);
    }

    @ExceptionHandler({StockNotFoundException.class, ResponseStatusException.class, Exception.class})
    public ResponseEntity<String> handleException(Exception e){
        if(e instanceof StockNotFoundException){
            return ResponseEntity.notFound().build();
        }else if(e instanceof ResponseStatusException){
            return ResponseEntity.badRequest().body(e.getMessage());

        }
        return ResponseEntity.unprocessableEntity().body("Unknown Error");
    }
}
