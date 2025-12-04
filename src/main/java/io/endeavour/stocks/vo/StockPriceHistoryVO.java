package io.endeavour.stocks.vo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StockPriceHistoryVO {
    private String tickerSymbol;
    private LocalDate tradingDate;
    private BigDecimal openPrice;
    private BigDecimal closePrice;
    private Double volume;

    public StockPriceHistoryVO(String tickerSymbol, LocalDate tradingDate, BigDecimal openPrice, BigDecimal closePrice, Double volume) {
        this.tickerSymbol = tickerSymbol;
        this.tradingDate = tradingDate;
        this.openPrice = openPrice;
        this.closePrice = closePrice;
        this.volume = volume;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public LocalDate getTradingDate() {
        return tradingDate;
    }

    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public Double getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "StockPriceHistoryVO{" +
                "tickerSymbol='" + tickerSymbol + '\'' +
                ", tradingDate=" + tradingDate +
                ", openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                ", volume=" + volume +
                '}';
    }
}
