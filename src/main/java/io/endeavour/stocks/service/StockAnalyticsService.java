package io.endeavour.stocks.service;


import io.endeavour.stocks.dao.StockPriceHistoryDAO;
import io.endeavour.stocks.vo.StockPriceHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockAnalyticsService {

    @Autowired
    private StockPriceHistoryDAO stockPriceHistoryDAO;

    public List<StockPriceHistoryVO> getSingleStockPriceHistory(String tickerSymbol){
        return stockPriceHistoryDAO.getSingleStockPriceHistory(tickerSymbol);
    }

}
