package io.endeavour.stocks.entity.stocks;

import io.endeavour.stocks.vo.TopStockBySectorVO;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "STOCK_FUNDAMENTALS", schema = "ENDEAVOUR")

@NamedNativeQuery(
        name = "StockFundamentals.topStockBySector",
        query = """
       WITH CAP_RANK_TABLE AS(
                                      SELECT
                                      	sl2.SECTOR_NAME,
                                      	sf.SECTOR_ID,
                                      	sl.TICKER_SYMBOL,
                                      	sl.TICKER_NAME,
                                      	sf.MARKET_CAP,
                                      	RANK () OVER (PARTITION BY sf.SECTOR_ID ORDER BY sf.MARKET_CAP desc) AS CAP_RANK
                                      FROM
                                      	ENDEAVOUR.STOCK_FUNDAMENTALS sf,
                                      	ENDEAVOUR.STOCKS_LOOKUP sl,
                                      	ENDEAVOUR.SECTOR_LOOKUP sl2
                                      WHERE
                                      sf.TICKER_SYMBOL = sl.TICKER_SYMBOL
                                      AND sf.SECTOR_ID = sl2.SECTOR_ID
                                      AND sf.MARKET_CAP IS NOT NULL
                                      )
                                      SELECT
                                      	SECTOR_NAME,
                                      	SECTOR_ID,
                                      	TICKER_SYMBOL,
                                      	TICKER_NAME,
                                      	MARKET_CAP
                                      FROM
                                      	CAP_RANK_TABLE
                                      WHERE
                                      	CAP_RANK = 1
                
   """,
        resultSetMapping = "StockFundamentals.topStockBySectorMapping"
)
@SqlResultSetMapping(name = "StockFundamentals.topStockBySectorMapping",
        classes = @ConstructorResult(targetClass = TopStockBySectorVO.class,
                columns = {
                        @ColumnResult(name = "SECTOR_ID", type = Integer.class),
                        @ColumnResult(name = "SECTOR_NAME", type = String.class),
                        @ColumnResult(name = "MARKET_CAP", type = BigDecimal.class),
                        @ColumnResult(name = "TICKER_SYMBOL", type = String.class),
                        @ColumnResult(name = "TICKER_NAME", type = String.class)
                }
        )
)

public class StockFundamentals {
    @Id
    @Column(name = "TICKER_SYMBOL")
    private String tickerSymbol;
    @Column(name = "SECTOR_ID")
    private Integer sectorId;
    @Column(name = "SUBSECTOR_ID")
    private Integer subSectorId;
    @Column(name = "MARKET_CAP")
    private BigDecimal marketCap;
    @Column(name = "CURRENT_RATIO")
    private BigDecimal currentRatio;

    @Transient
    private BigDecimal cumulativeReturn;

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public Integer getSectorId() {
        return sectorId;
    }

    public void setSectorId(Integer sectorId) {
        this.sectorId = sectorId;
    }

    public Integer getSubSectorId() {
        return subSectorId;
    }

    public void setSubSectorId(Integer subSectorId) {
        this.subSectorId = subSectorId;
    }

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }

    public BigDecimal getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(BigDecimal currentRatio) {
        this.currentRatio = currentRatio;
    }

    public BigDecimal getCumulativeReturn() {
        return cumulativeReturn;
    }

    public void setCumulativeReturn(BigDecimal cumulativeReturn) {
        this.cumulativeReturn = cumulativeReturn;
    }

    @Override
    public String toString() {
        return "StockFundamentals{" +
                "tickerSymbol='" + tickerSymbol + '\'' +
                ", sectorId=" + sectorId +
                ", subSectorId=" + subSectorId +
                ", marketCap=" + marketCap +
                ", currentRatio=" + currentRatio +
                '}';
    }
}
