package io.endeavour.stocks.vo;

import java.math.BigDecimal;

public class TopStockBySectorVO {
    private Integer sectorId;
    private String  sectorName;
    private BigDecimal marketCap;
    private String tickerSymbol;
    private String tickerName;

    public TopStockBySectorVO(Integer sectorId, String sectorName, BigDecimal marketCap, String tickerSymbol, String tickerName) {
        this.sectorId = sectorId;
        this.sectorName = sectorName;
        this.marketCap = marketCap;
        this.tickerSymbol = tickerSymbol;
        this.tickerName = tickerName;
    }

    public Integer getSectorId() {
        return sectorId;
    }

    public void setSectorId(Integer sectorId) {
        this.sectorId = sectorId;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public String getTickerName() {
        return tickerName;
    }

    public void setTickerName(String tickerName) {
        this.tickerName = tickerName;
    }

    @Override
    public String toString() {
        return "TopStockBySectorVO{" +
                "sectorId=" + sectorId +
                ", sectorName='" + sectorName + '\'' +
                ", marketCap=" + marketCap +
                ", tickerSymbol='" + tickerSymbol + '\'' +
                ", tickerName='" + tickerName + '\'' +
                '}';
    }
}
