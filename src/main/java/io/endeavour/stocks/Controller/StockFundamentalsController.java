package io.endeavour.stocks.Controller;

import io.endeavour.stocks.service.StockFundamentalsService;
import io.endeavour.stocks.vo.StockFundamentalsVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class StockFundamentalsController {

    @Autowired
    private StockFundamentalsService service;

    @PostMapping("/stock-fundamentals")
    public List<StockFundamentalsVO> getFundamentals(@RequestBody List<String> tickers) {
        return service.getFundamentals(tickers);
    }
}
