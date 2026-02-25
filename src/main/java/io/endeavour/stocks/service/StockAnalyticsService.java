package io.endeavour.stocks.service;


import io.endeavour.stocks.dao.LookupDAO;
import io.endeavour.stocks.dao.StockFundamentalsDAO;
import io.endeavour.stocks.dao.StockPriceHistoryDAO;
import io.endeavour.stocks.entity.stocks.*;
import io.endeavour.stocks.repository.stocks.SectorLookupRepository;
import io.endeavour.stocks.repository.stocks.StockFundamentalsRepository;
import io.endeavour.stocks.repository.stocks.StockPriceHistoryRepository;
import io.endeavour.stocks.repository.stocks.SubSectorRepository;
import io.endeavour.stocks.vo.SectorLookupVO;
import io.endeavour.stocks.vo.StockFundamentalsVO;
import io.endeavour.stocks.vo.StockPriceHistoryVO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;


@Service
public class StockAnalyticsService {

    private static Logger LOGGER= LoggerFactory.getLogger(StockAnalyticsService.class);
    @Autowired
    private StockPriceHistoryDAO stockPriceHistoryDAO;
    private LookupDAO lookupDAO;
    private StockFundamentalsDAO stockFundamentalsDAO;

    @Autowired
    StockFundamentalsRepository stockFundamentalsRepository;

    @Autowired
    SectorLookupRepository sectorLookupRepository;

    @Autowired
    SubSectorRepository subSectorRepository;

    @Autowired
    StockPriceHistoryRepository stockPriceHistoryRepository;

@Autowired
    public StockAnalyticsService(StockPriceHistoryDAO stockPriceHistoryDAO,LookupDAO lookupDAO,StockFundamentalsDAO stockFundamentalsDAO){
    this.stockPriceHistoryDAO=stockPriceHistoryDAO;
    this.lookupDAO=lookupDAO;
    this.stockFundamentalsDAO=stockFundamentalsDAO;
}
    public List<StockPriceHistoryVO> getSingleStockPriceHistory(String tickerSymbol){
        return stockPriceHistoryDAO.getSingleStockPriceHistory(tickerSymbol);
    }

    public List<StockPriceHistoryVO> getStockPriceHistoryP(List<String> tickerSymbols, LocalDate fromDate, LocalDate toDate, Optional<String> sortFieldOptional,
                                                           Optional<String> sortDirectionOptional){

    LOGGER.info("getting stock price history for ticker symbols"+ tickerSymbols);
    List<StockPriceHistoryVO> stockPriceHistoryVOList=stockPriceHistoryDAO.getStockPriceHistoryForDateRange(tickerSymbols,fromDate,toDate);

    String sortField=sortFieldOptional.orElse("tradingDate");
    String sortDirection="ASC";
    if(sortDirectionOptional.isPresent()){
        sortDirection=sortDirectionOptional.get();
    }

        Comparator sortComparator=switch (sortField){
        case("openPrice")->Comparator.comparing(StockPriceHistoryVO::getOpenPrice);
            case("closePrice")->Comparator.comparing(StockPriceHistoryVO::getClosePrice);
            case("volume")->Comparator.comparing(StockPriceHistoryVO::getVolume);
            case("tradingDate")->Comparator.comparing(StockPriceHistoryVO::getTradingDate);
            default -> throw new IllegalStateException("unexpected value :"+sortField);
        };

    if(sortDirection.equalsIgnoreCase("dcs")){
        sortComparator=sortComparator.reversed();
    }
    stockPriceHistoryVOList.sort(sortComparator);
    return stockPriceHistoryVOList;
    }

    public List<StockPriceHistoryVO> getStockPriceHistory(String tickerSymbol, LocalDate frmDate, LocalDate toDate){
    return stockPriceHistoryDAO.getStockPriceHistory(tickerSymbol,frmDate,toDate);
    }

    public List<SectorLookupVO> getSectorLookups(){
        return lookupDAO.getSectorLookups();
    }

    public SectorLookupVO getSectorLookupById(Integer sectorId){
    return lookupDAO.getSectorLookupBySectorId(sectorId);
    }

    public List<StockFundamentalsVO> getFundamentals(List<String> tickers){
    return stockFundamentalsDAO.getFundamentals(tickers);
    }

    public List<StockFundamentals> getStockFundamentalsUsingJPA(){
    List<StockFundamentals> stockFundamentalsList=stockFundamentalsRepository.findAll();
    return stockFundamentalsList;
    }

    public List<StockFundamentals> getStockFundamentalsMarketCapNotNull() {
        List<StockFundamentals> stockFundamentalsList = stockFundamentalsRepository.findAllByMarketCapNotNull();
        return stockFundamentalsList;
    }

    public List<StockFundamentals> getStockFundamentalsMarketCapGreaterThan(BigDecimal marketCap) {
        List<StockFundamentals> stockFundamentalsList = stockFundamentalsRepository.findAllByMarketCapGreaterThan(marketCap);
        return stockFundamentalsList;
    }

    public List<SectorLookup> getAllSectorsUsingJPA(){
    List<SectorLookup> sectorLookupList= sectorLookupRepository.findAll();
    return sectorLookupList;
    }

    public SectorLookup getSectorById(Integer id){
    return sectorLookupRepository.findById(id).orElse(null);
    }

    public List<SubSectorLookup> getSubSectorUsingJPA(){
    List<SubSectorLookup> subSectorLookupList=subSectorRepository.findAll();
    return subSectorLookupList;
    }

    public Optional<StockPriceHistory> getStockPriceHistoryForTickerSymbol(String tickerSymbol, LocalDate tradingDate){
        StockPriceHistoryPrimaryKey stockPriceHistoryPrimaryKey=new StockPriceHistoryPrimaryKey();
        stockPriceHistoryPrimaryKey.setTickerSymbol(tickerSymbol);
        stockPriceHistoryPrimaryKey.setTradingDate(tradingDate);
        Optional<StockPriceHistory> optionalStockPriceHistory = stockPriceHistoryRepository.findById(stockPriceHistoryPrimaryKey);
        return optionalStockPriceHistory;
    }

    public List<StockFundamentals> getStockFundamentalsUsingJPQL() {
    return stockFundamentalsRepository.findAllMarketCapByJPQL();
    }

    public List<StockFundamentals> getStockFundamentalsUsingNativeSQL(){
    return stockFundamentalsRepository.findAllMarketCapNotNullUsingNativeSQL();
    }
}
