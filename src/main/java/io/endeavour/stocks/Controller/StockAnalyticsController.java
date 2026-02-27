package io.endeavour.stocks.Controller;


import io.endeavour.stocks.entity.stocks.SectorLookup;
import io.endeavour.stocks.entity.stocks.StockFundamentals;
import io.endeavour.stocks.entity.stocks.StockPriceHistory;
import io.endeavour.stocks.entity.stocks.SubSectorLookup;
import io.endeavour.stocks.service.StockAnalyticsService;
import io.endeavour.stocks.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stocks")
@Tag(name="Stocks" ,description = "APIs for stocks ")
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

    @GetMapping("/stock-price-history-date-range/{tickerSymbol}")
    @Operation(method = "singleStockPriceHistory", description = "get stock price history from db for date range")
    @ApiResponse(responseCode = "400",description = "return 400 if fromdate is after toDate")
    public List<StockPriceHistoryVO> getStockPriceHistory(@PathVariable String tickerSymbol,
                                                          @RequestParam @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate fromDate,
                                                          @RequestParam @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate toDate){
        if(fromDate.compareTo(toDate)>0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"fromdate cannot be after todate");
        }
        return stockAnalyticsService.getStockPriceHistory(tickerSymbol,fromDate,toDate);
    }

@PostMapping("/stock-price-history")
public List<StockPriceHistoryVO> getStockPriceHistoryP(@RequestBody StockPriceHistoryRequest stockPriceHistoryRequest,
                                                       @RequestParam(name="sortField",required = false)Optional<String> sortFieldOptional,
                                                       @RequestParam(name="sortDirection",required = false)Optional<String> sortDirectionOptional){

    if(stockPriceHistoryRequest.getFromDate().compareTo(stockPriceHistoryRequest.getToDate())>0){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"fromdate cannot be after todate");
    }
    return stockAnalyticsService.getStockPriceHistoryP(stockPriceHistoryRequest.getTickerSymbolList(),stockPriceHistoryRequest.getFromDate(),stockPriceHistoryRequest.getToDate(),
            sortFieldOptional,sortDirectionOptional);
}

    @GetMapping("/sectors")
    public List<SectorLookupVO> getSectorLookups(){
        return stockAnalyticsService.getSectorLookups();
    }

    @GetMapping("/sectors/{sectorId}")
    public SectorLookupVO getSectorLookupById(@PathVariable Integer sectorId){
        return stockAnalyticsService.getSectorLookupById(sectorId);
    }

    @PostMapping("/stock-fundamentals")
    public List<StockFundamentalsVO> getFundamentals(@RequestBody List<String> tickers){
        return stockAnalyticsService.getFundamentals(tickers);
    }

    @GetMapping("/stock-fundamentals-usingJPA")
    public List<StockFundamentals> getStockFundamentalsUsingJPA(){
        return stockAnalyticsService.getStockFundamentalsUsingJPA();
    }

    @GetMapping("/stock-fundamentals-market-cap")
    public List<StockFundamentals> getStockFundamentalsMarketCap(@RequestParam String format,@RequestParam(required = false)BigDecimal marketCap) {
        List<StockFundamentals> stockFundamentals=new ArrayList<>();
        if (marketCap != null) {
            stockFundamentals= stockAnalyticsService.getStockFundamentalsMarketCapGreaterThan(marketCap);
        } else {
            if ("dsl".equalsIgnoreCase(format)){
                stockFundamentals= stockAnalyticsService.getStockFundamentalsMarketCapNotNull();
            }
            else if("jpql".equalsIgnoreCase(format)){
                stockFundamentals= stockAnalyticsService.getStockFundamentalsUsingJPQL();
            }
            else if("sql".equalsIgnoreCase(format)){
                stockFundamentals= stockAnalyticsService.getStockFundamentalsUsingNativeSQL();
            }
        }
        return stockFundamentals;
    }


    @GetMapping("/sector-lookup-usingJPA")
    public List<SectorLookup> getAllSectorsUsingJPA(){
        return stockAnalyticsService.getAllSectorsUsingJPA();
    }

    @GetMapping("/sector-lookup-JPA/{sectorID}")
    public SectorLookup getSectorById(@PathVariable Integer sectorID){
        SectorLookup sectorLookup= stockAnalyticsService.getSectorById(sectorID);
        if(sectorLookup==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sector not found");
        }
        return sectorLookup;
    }

    @GetMapping("/subsector-lookup-usingJPA")
    public List<SubSectorLookup> getSubSectorUsingJPA(){
        return stockAnalyticsService.getSubSectorUsingJPA();
    }

    @GetMapping("/stock-price-history/{tickerSymbol}/{tradingDate}")
    public ResponseEntity<StockPriceHistory> getStockPriceHistory(@PathVariable String tickerSymbol,@PathVariable @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate tradingDate){
    return ResponseEntity.of(stockAnalyticsService.getStockPriceHistoryForTickerSymbol(tickerSymbol,tradingDate));
    }

    @GetMapping("/stock-fundamentals/{tickerSymbol}")
    public ResponseEntity<StockFundamentalHistoryVO> getStockFundamentalHistory(@PathVariable(value = "tickerSymbol") String tickerSymbol,
                                                                                @RequestParam(value = "fromDate") @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate fromDate,
                                                                                @RequestParam(value = "toDate") @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate toDate){
        return ResponseEntity.of(stockAnalyticsService.getStockFundamentalHistory(tickerSymbol,fromDate,toDate));
    }

    @GetMapping("/stock-fundamentals/top-stock-by-sector")
    public List<TopStockBySectorVO> getTopStockBySector(){
        return stockAnalyticsService.getTopStockBySector();
    }

    @GetMapping(value="/stock-fundamentals/top-cumulative-return")
    public List<StockFundamentals> getCumulativeReturn(@RequestParam(value = "fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
                                                       @RequestParam(value = "toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,
                                                       @RequestParam(value="num")Integer num)
    {
        return stockAnalyticsService.getCumulativeReturn(fromDate,toDate,num);
    }
    }

