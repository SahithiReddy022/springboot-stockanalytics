package io.endeavour.stocks.Controller;


import io.endeavour.stocks.service.StockAnalyticsService;
import io.endeavour.stocks.vo.Stock;
import io.endeavour.stocks.vo.StockPriceHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockAnalyticsController {

    @Autowired
    public StockAnalyticsService stockAnalyticsService;
    @GetMapping("/dummy")
    public Stock dummystock(){
        Stock stock=new Stock();
        stock.setTickerSymbol("AAPl");
        stock.setTickerName("Apple");
        return stock;
    }

    @GetMapping("/stock-price-history/{tickerSymbol}")
    public List<StockPriceHistoryVO> getSingleStockPriceHistory(@PathVariable String tickerSymbol){
        return stockAnalyticsService.getSingleStockPriceHistory(tickerSymbol);
    }

}
