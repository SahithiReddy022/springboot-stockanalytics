package io.endeavour.stocks.entity.stocks;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(schema = "ENDEAVOUR", name = "STOCKS_PRICE_HISTORY")
@IdClass(StockPriceHistoryPrimaryKey.class)
public class StockPriceHistoryEntity {
    @Column(name = "TICKER_SYMBOL")
    @Id
    private String tickerSymbol;
    @Column(name = "TRADING_DATE")
    @Id
    private LocalDate tradingDate;
    @Column(name = "OPEN_PRICE")
    private BigDecimal openPrice;
    @Column(name = "CLOSE_PRICE")
    private BigDecimal closePrice;
    @Column(name = "VOLUME")
    private long volume;

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

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }
}
