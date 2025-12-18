package io.endeavour.stocks.service;

import io.endeavour.stocks.controller.StocksController;
import io.endeavour.stocks.dao.StockPriceHistoryDAO;
import io.endeavour.stocks.entity.stocks.StockFundamentalsEntity;
import io.endeavour.stocks.entity.stocks.StockPriceHistoryEntity;
import io.endeavour.stocks.entity.stocks.StockPriceHistoryPrimaryKey;
import io.endeavour.stocks.exception.StockNotFoundException;
import io.endeavour.stocks.mapper.StockEntityMapper;
import io.endeavour.stocks.repository.stocks.StockFundamentalsRepository;
import io.endeavour.stocks.repository.stocks.StockPriceHistoryRepository;
import io.endeavour.stocks.vo.StockFundamentalsVO;
import io.endeavour.stocks.vo.StockPriceHistoryVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MarketAnalyticService {

    private static Logger LOGGER = LoggerFactory.getLogger(MarketAnalyticService.class);
    @Autowired
    private StockPriceHistoryDAO stockPriceHistoryDAO;

    @Autowired
    private StockPriceHistoryRepository stockPriceHistoryRepository;

    @Autowired
    private StockFundamentalsRepository stockFundamentalsRepository;

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
            LOGGER.error("stockPriceHistoryVOList is empty. Check the request");
            throw new StockNotFoundException("No data found");
        }
        return stockPriceHistoryVOList;
    }

    public List<StockFundamentalsVO> getStockFundamentalsEntities(){
        List<StockFundamentalsEntity> stockFundamentalsEntities = stockFundamentalsRepository.findAll();

        List<StockFundamentalsVO> stockFundamentalsVOList = StockEntityMapper.map(stockFundamentalsEntities);

        return stockFundamentalsVOList;
    }

    public Optional<StockPriceHistoryEntity> getStockPriceHistory(String tickerSymbol,
                                                                  LocalDate tradingDate){
        StockPriceHistoryPrimaryKey stockPriceHistoryPrimaryKey = new StockPriceHistoryPrimaryKey();
        stockPriceHistoryPrimaryKey.setTickerSymbol(tickerSymbol);
        stockPriceHistoryPrimaryKey.setTradingDate(tradingDate);
        return stockPriceHistoryRepository.findById(stockPriceHistoryPrimaryKey);
    }


}
