package io.endeavour.stocks.controller;

import io.endeavour.stocks.service.MarketAnalyticService;
import io.endeavour.stocks.vo.StockPriceHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StocksController {

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
}
