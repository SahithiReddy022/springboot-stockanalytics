package io.endeavour.stocks.entity.stocks;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class StockPriceHistoryPrimaryKey implements Serializable {

    private String tickerSymbol;
    private LocalDate tradingDate;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StockPriceHistoryPrimaryKey that = (StockPriceHistoryPrimaryKey) o;
        return Objects.equals(tickerSymbol, that.tickerSymbol) && Objects.equals(tradingDate, that.tradingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tickerSymbol, tradingDate);
    }

    @Override
    public String toString() {
        return "StockPriceHistoryPrimaryKey{" +
                "tickerSymbol='" + tickerSymbol + '\'' +
                ", tradingDate=" + tradingDate +
                '}';
    }
}
