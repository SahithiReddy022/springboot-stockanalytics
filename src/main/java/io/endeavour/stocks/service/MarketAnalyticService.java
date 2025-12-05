package io.endeavour.stocks.service;

import io.endeavour.stocks.dao.StockPriceHistoryDAO;
import io.endeavour.stocks.exception.StockNotFoundException;
import io.endeavour.stocks.vo.StockPriceHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MarketAnalyticService {

    @Autowired
    private StockPriceHistoryDAO stockPriceHistoryDAO;

    public StockPriceHistoryVO getDummyStockPriceHistoryVO() {
        return stockPriceHistoryDAO.getDummyStockPriceHistoryVO();
    }

    public List<StockPriceHistoryVO> getStockPriceHistoryVOList(String ticker){
        List<StockPriceHistoryVO> singleStockPriceHistory = stockPriceHistoryDAO.getSingleStockPriceHistory(ticker);
        if(singleStockPriceHistory.isEmpty()){
            throw new StockNotFoundException("No data found for ticker "+ ticker);
        }
        return singleStockPriceHistory;
    }

    public List<StockPriceHistoryVO> getStockPriceHistoryForTickersForDateRange(List<String> tickers,
                                                                                LocalDate fromDate,
                                                                                LocalDate toDate){
        List<StockPriceHistoryVO> stockPriceHistoryVOList = stockPriceHistoryDAO.getStockPriceHistoryForTickersForDateRang(
                tickers, fromDate, toDate);
        if(stockPriceHistoryVOList.isEmpty()){
            throw new StockNotFoundException("No data found");
        }
        return stockPriceHistoryVOList;
    }
}
