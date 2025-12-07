package io.endeavour.stocks.entity.stocks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="STOCK_FUNDAMENTALS", schema = "ENDEAVOUR")
public class StockFundamentalsEntity {
    @Column(name="TICKER_SYMBOL")
    @Id
    private String tickerSymbol;
    @Column(name="SECTOR_ID")
    private Integer sectorId;
    @Column(name="SUBSECTOR_ID")
    private Integer subSectorId;
    @Column(name="MARKET_CAP")
    private BigDecimal marketCap;
    @Column(name="CURRENT_RATIO")
    private Double currentRatio;

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

    public Double getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(Double currentRatio) {
        this.currentRatio = currentRatio;
    }

    @Override
    public String toString() {
        return "StockFundamentalsEntity{" +
                "tickerSymbol='" + tickerSymbol + '\'' +
                ", sectorId=" + sectorId +
                ", subSectorId=" + subSectorId +
                ", marketCap=" + marketCap +
                ", currentRatio=" + currentRatio +
                '}';
    }
}
