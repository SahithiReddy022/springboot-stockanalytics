package io.endeavour.stocks.service;

import io.endeavour.stocks.dao.StockFundamentalDAO;
import io.endeavour.stocks.dao.StockPriceHistoryDAO;
import io.endeavour.stocks.entity.stocks.StockFundamentalsEntity;
import io.endeavour.stocks.entity.stocks.StockPriceHistoryEntity;
import io.endeavour.stocks.entity.stocks.StockPriceHistoryPrimaryKey;
import io.endeavour.stocks.exception.StockNotFoundException;
import io.endeavour.stocks.mapper.StockEntityMapper;
import io.endeavour.stocks.repository.stocks.StockFundamentalsRepository;
import io.endeavour.stocks.repository.stocks.StockPriceHistoryRepository;
import io.endeavour.stocks.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MarketAnalyticService {

    private static Logger LOGGER = LoggerFactory.getLogger(MarketAnalyticService.class);
    @Autowired
    private StockPriceHistoryDAO stockPriceHistoryDAO;

    @Autowired
    private StockPriceHistoryRepository stockPriceHistoryRepository;

    @Autowired
    private StockFundamentalsRepository stockFundamentalsRepository;

    @Autowired
    private StockFundamentalDAO stockFundamentalDAO;

    @Autowired
    private StockCalculationsClient stockCalculationsClient;


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

    public List<StockFundamentalsEntity> getTopStockFundamentalEntities(String format) {

        List<StockFundamentalsEntity> stockFundamentalsEntities = null;
        if (format == null || format.isEmpty()) {
            stockFundamentalsEntities= stockFundamentalsRepository.findByCurrentRatioIsNotNull();
        }else if (format.equalsIgnoreCase("jpql")) {
            stockFundamentalsEntities= stockFundamentalsRepository.getStockFundamentalsUsingJPQL();
        } else if (format.equalsIgnoreCase("sql")) {
            stockFundamentalsEntities= stockFundamentalsRepository.getStockFundamentalsUsingSql();
        }
        return stockFundamentalsEntities;

    }

    public List<StockFundamentalsEntity> getTopNStockFundamentalEntities(String format,
                                                                          Integer limit){
        List<StockFundamentalsEntity>  stockFundamentalsEntities = null;
        if (format.equalsIgnoreCase("jpql")) {
            stockFundamentalsEntities = stockFundamentalDAO.getTopNStockFundamentalsEntitiesUsingJPQL(limit);
        }else if(format.equalsIgnoreCase("cb")){
            stockFundamentalsEntities = stockFundamentalDAO.getTopNStockFundamentalsEntitiesUsingCriteria(limit);
        }
        else {
            stockFundamentalsEntities = stockFundamentalsRepository.getTopNStockFundamentals(limit);
        }
        return stockFundamentalsEntities;
    }

    public List<TopStockBySectorVO> getTopStockBySectorVOList(){
        return stockFundamentalsRepository.getTopStockBySectorVO();
    }

    public List<StockFundamentalsEntity> getCumulativeReturn(LocalDate fromDate, LocalDate toDate){
        List<StockFundamentalsEntity> stockFundamentalsEntities = stockFundamentalsRepository.findAll();

        Map<String, StockFundamentalsEntity> stockEntityMap = stockFundamentalsEntities.stream()
                .collect(Collectors.toMap(StockFundamentalsEntity::getTickerSymbol, Function.identity()));

        List<String> tickers = stockFundamentalsEntities.stream()
                .map(stockFundamentalsEntity -> stockFundamentalsEntity.getTickerSymbol())
                .toList();

        CumulativeReturnInputVO cumulativeReturnInputVO = new CumulativeReturnInputVO();
        cumulativeReturnInputVO.setTickers(tickers);
        List<CumulativeReturnOutputVO> cumulativeReturnOutputVOS = stockCalculationsClient.getCumulativeReturns(fromDate, toDate, cumulativeReturnInputVO);

        if(cumulativeReturnOutputVOS == null ){
            throw new RuntimeException("Service is down");
        }

        cumulativeReturnOutputVOS.forEach(
                cumulativeReturnOutputVO ->
                {
                    String tickerSymbol = cumulativeReturnOutputVO.getTickerSymbol();
                    StockFundamentalsEntity stockFundamentalsEntity = stockEntityMap.get(tickerSymbol);
                    stockFundamentalsEntity.setCumulativeReturn(cumulativeReturnOutputVO.getCumulativeReturn());
                }
        );

        List<StockFundamentalsEntity> finalList = stockFundamentalsEntities.stream()
                .filter(stockFundamentalsEntity -> stockFundamentalsEntity.getCumulativeReturn() != null)
                .sorted(Comparator.comparing(StockFundamentalsEntity::getCumulativeReturn))
                .toList();
        return finalList;

    }







}
