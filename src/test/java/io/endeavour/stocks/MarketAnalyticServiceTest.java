package io.endeavour.stocks;

import io.endeavour.stocks.entity.stocks.StockFundamentalsEntity;
import io.endeavour.stocks.repository.stocks.StockFundamentalsRepository;
import io.endeavour.stocks.service.MarketAnalyticService;
import io.endeavour.stocks.service.StockCalculationsClient;
import io.endeavour.stocks.vo.CumulativeReturnOutputVO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MarketAnalyticServiceTest {

    @MockBean
    private StockFundamentalsRepository stockFundamentalsRepository;

    @MockBean
    private StockCalculationsClient stockCalculationsClient;

    @Autowired
    private MarketAnalyticService marketAnalyticService;

    @Test
    public void whenGetCumulativeReturnIsSuccessful(){

        Mockito.when(stockFundamentalsRepository.findAll())
                .thenReturn(buildMockEntities());

        Mockito.when(stockCalculationsClient.getCumulativeReturns(
                        Mockito.any(), Mockito.any(), Mockito.any()
                ))
                .thenReturn(buildMockCumulativeOutputVO());

        List<StockFundamentalsEntity> stockFundamentalsEntityList = marketAnalyticService.getCumulativeReturn(
                LocalDate.now().minusMonths(1), LocalDate.now());

        assertNotNull(stockFundamentalsEntityList);

        assertEquals(2, stockFundamentalsEntityList.size());

        StockFundamentalsEntity stockFundamentalsEntity = stockFundamentalsEntityList.get(0);
        assertEquals("TSLA", stockFundamentalsEntity.getTickerSymbol());
        assertEquals(new BigDecimal("2.5"), stockFundamentalsEntity.getCumulativeReturn());

        stockFundamentalsEntity = stockFundamentalsEntityList.get(1);
        assertEquals("AAPL", stockFundamentalsEntity.getTickerSymbol());
        assertEquals(new BigDecimal("3.5"), stockFundamentalsEntity.getCumulativeReturn());

    }

    @Test
    public void whenCumulativeServiceIsDown(){
        Mockito.when(stockFundamentalsRepository.findAll())
                .thenReturn(buildMockEntities());

        Mockito.when(stockCalculationsClient.getCumulativeReturns(
                        Mockito.any(), Mockito.any(), Mockito.any()
                ))
                .thenReturn(null);

        try {
            List<StockFundamentalsEntity> stockFundamentalsEntityList = marketAnalyticService.getCumulativeReturn(
                    LocalDate.now().minusMonths(1), LocalDate.now());
            fail("Expected exception to be thrown");
        }catch (Exception e){
            //ignore
        }


    }

    private List<StockFundamentalsEntity> buildMockEntities(){
        List<StockFundamentalsEntity> mockStockFundamentalsEntities = new ArrayList<>();

        StockFundamentalsEntity stockFundamentalsEntity = new StockFundamentalsEntity();
        stockFundamentalsEntity.setTickerSymbol("AAPL");
        mockStockFundamentalsEntities.add(stockFundamentalsEntity);
        stockFundamentalsEntity = new StockFundamentalsEntity();
        stockFundamentalsEntity.setTickerSymbol("TSLA");
        mockStockFundamentalsEntities.add(stockFundamentalsEntity);

        return mockStockFundamentalsEntities;
    }

    private List<CumulativeReturnOutputVO> buildMockCumulativeOutputVO(){
        List<CumulativeReturnOutputVO> outputVOList = new ArrayList<>();
        CumulativeReturnOutputVO cumulativeReturnOutputVO = new CumulativeReturnOutputVO();
        cumulativeReturnOutputVO.setTickerSymbol("AAPL");
        cumulativeReturnOutputVO.setCumulativeReturn(new BigDecimal("3.5"));
        outputVOList.add(cumulativeReturnOutputVO);

        cumulativeReturnOutputVO = new CumulativeReturnOutputVO();
        cumulativeReturnOutputVO.setTickerSymbol("TSLA");
        cumulativeReturnOutputVO.setCumulativeReturn(new BigDecimal("2.5"));
        outputVOList.add(cumulativeReturnOutputVO);

        return outputVOList;
    }
}

