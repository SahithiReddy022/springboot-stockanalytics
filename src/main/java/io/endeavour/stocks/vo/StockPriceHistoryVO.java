package io.endeavour.stocks.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = " Each Stock Price History object")
public class StockPriceHistoryVO {
    @Schema(name="tickerSymbol",example = "TSLA")
    private String tickerSymbol;
    @Schema(name="tradingDate",example = "2025-12-20")
    private LocalDate tradingDate;
    @Schema(name="openPrice",example = "23.60")
    private BigDecimal openPrice;
    @Schema(name="closePrice",example = "25.7")
    private BigDecimal closePrice;
    @Schema(name="volume",example = "1000")
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

    public Boolean getIsGoodStock(){
        double close = this.closePrice.doubleValue();
        double open = this.openPrice.doubleValue();
        return close > open;
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
