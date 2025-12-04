package io.endeavour.stocks.service;

import io.endeavour.stocks.dao.StockPriceHistoryDAO;
import io.endeavour.stocks.vo.StockPriceHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketAnalyticService {

    @Autowired
    private StockPriceHistoryDAO stockPriceHistoryDAO;

    public StockPriceHistoryVO getDummyStockPriceHistoryVO() {
        return stockPriceHistoryDAO.getDummyStockPriceHistoryVO();
    }

    public List<StockPriceHistoryVO> getStockPriceHistoryVOList(String ticker){
        return stockPriceHistoryDAO.getSingleStockPriceHistory(ticker);
    }
}
