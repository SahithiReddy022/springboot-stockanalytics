package io.endeavour.stocks.entity.stocks;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "STOCKS_PRICE_HISTORY", schema = "ENDEAVOUR")
@IdClass(StockPriceHistoryPrimaryKey.class)
public class StockPriceHistory {

    @Id
    @Column(name = "TICKER_SYMBOL")
    private String tickerSymbol;

    @Id
    @Column(name = "TRADING_DATE")
    private LocalDate tradingDate;

    @Column(name = "OPEN_PRICE")
    private BigDecimal openPrice;

    @Column(name = "CLOSE_PRICE")
    private BigDecimal closePrice;

    @Column(name = "volume")
    private Long volume;

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public LocalDate getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(LocalDate tradingDate) {
        this.tradingDate = tradingDate;
    }

    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    public BigDecimal getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(BigDecimal closePrice) {
        this.closePrice = closePrice;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "StockPriceHistory{" +
                "tickerSymbol='" + tickerSymbol + '\'' +
                ", tradingDate=" + tradingDate +
                ", openPrice=" + openPrice +
                ", closePrice=" + closePrice +
                ", volume=" + volume +
                '}';
    }
}
