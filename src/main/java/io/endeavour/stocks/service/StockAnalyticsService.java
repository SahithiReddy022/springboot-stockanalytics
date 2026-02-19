package io.endeavour.stocks.service;


import io.endeavour.stocks.dao.LookupDAO;
import io.endeavour.stocks.dao.StockPriceHistoryDAO;
import io.endeavour.stocks.vo.SectorLookupVO;
import io.endeavour.stocks.vo.StockPriceHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class StockAnalyticsService {

    @Autowired
    private StockPriceHistoryDAO stockPriceHistoryDAO;
    private LookupDAO lookupDAO;

@Autowired
    public StockAnalyticsService(StockPriceHistoryDAO stockPriceHistoryDAO,LookupDAO lookupDAO){
    this.stockPriceHistoryDAO=stockPriceHistoryDAO;
    this.lookupDAO=lookupDAO;
}
    public List<StockPriceHistoryVO> getSingleStockPriceHistory(String tickerSymbol){
        return stockPriceHistoryDAO.getSingleStockPriceHistory(tickerSymbol);
    }

    public List<StockPriceHistoryVO> getStockPriceHistoryP(List<String> tickerSymbols, LocalDate fromDate, LocalDate toDate, Optional<String> sortFieldOptional,
                                                           Optional<String > sortDirectionOptional){

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

}
