package io.endeavour.stocks;

import io.endeavour.stocks.entity.stocks.StockFundamentals;
import io.endeavour.stocks.repository.stocks.StockFundamentalsRepository;
import io.endeavour.stocks.service.StockAnalyticsService;
import io.endeavour.stocks.service.StockClientCalculation;
import io.endeavour.stocks.vo.remote.CumulativeReturnWSOutputVO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jdbc.JdbcRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
//@EnableAutoConfiguration(exclude = {
//        DataSourceAutoConfiguration.class,
//        JdbcRepositoriesAutoConfiguration.class,
//        HibernateJpaAutoConfiguration.class
//})
@ActiveProfiles({"test"})
public class StockAnalyticsServiceTest {

    @Autowired
    StockAnalyticsService stockAnalyticsService;

    @MockBean
    StockFundamentalsRepository stockFundamentalsRepository;

    @MockBean
    StockClientCalculation stockClientCalculation;

    @Test
    public void whenCallingCumulativeReturnIsSuccessful(){

        List<StockFundamentals> mockStockFundamentals = List.of(createMockStockFundamentals("AAPL", new BigDecimal("150")),
                createMockStockFundamentals("TSLA", new BigDecimal("100")),
                createMockStockFundamentals("AMD", new BigDecimal("70")),
                createMockStockFundamentals("GOOGL", new BigDecimal("125")));

        Mockito.when(stockFundamentalsRepository.findAll()).thenReturn(mockStockFundamentals);

        List<CumulativeReturnWSOutputVO> mockCumulativeReturnOutputVOList = List.of(createMockCumulativeReturnOutputVO("AAPL", new BigDecimal("10")),
                createMockCumulativeReturnOutputVO("TSLA", new BigDecimal("5")),
                createMockCumulativeReturnOutputVO("GOOGL", new BigDecimal("30")),
                createMockCumulativeReturnOutputVO("AMD", new BigDecimal("1")));

        Mockito.when(stockClientCalculation.getCumulativeReturns(Mockito.any(),Mockito.any(),Mockito.any()))
                .thenReturn(mockCumulativeReturnOutputVOList);

        LocalDate fromDate=LocalDate.now().minusDays(3);
        LocalDate toDate=LocalDate.now();
        List<StockFundamentals> stockFundamentalsList= stockAnalyticsService.getCumulativeReturn(fromDate,toDate,2);

        assertNotNull(stockFundamentalsList);
        assertEquals(2,stockFundamentalsList.size());

        StockFundamentals stockFundamentals=stockFundamentalsList.get(0);
        assertEquals("GOOGL",stockFundamentals.getTickerSymbol());

        stockFundamentals=stockFundamentalsList.get(1);
        assertEquals("AAPL",stockFundamentals.getTickerSymbol());

    }

    private CumulativeReturnWSOutputVO createMockCumulativeReturnOutputVO(String tickerSymbol, BigDecimal cumulativeReturn) {
        CumulativeReturnWSOutputVO cumulativeReturnWSOutputVO=new CumulativeReturnWSOutputVO();
        cumulativeReturnWSOutputVO.setTickerSymbol(tickerSymbol);
        cumulativeReturnWSOutputVO.setCumulativeReturn(cumulativeReturn);
        return cumulativeReturnWSOutputVO;
    }

    private StockFundamentals createMockStockFundamentals(String tickerSymbol, BigDecimal marketCap){
        StockFundamentals stockFundamentals=new StockFundamentals();
        stockFundamentals.setTickerSymbol(tickerSymbol);
        stockFundamentals.setMarketCap(marketCap);
        return stockFundamentals;
    }
}
