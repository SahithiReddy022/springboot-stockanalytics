package io.endeavour.stocks.vo;

import java.math.BigDecimal;
import java.util.List;

public class StockFundamentalHistoryVO {

    private String tickerSymbol;

    private BigDecimal currentRatio;

    private BigDecimal marketCap;

    public List<StockPriceHistoryVO> tradingHistory;

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public BigDecimal getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(BigDecimal currentRatio) {
        this.currentRatio = currentRatio;
    }

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }

    public List<StockPriceHistoryVO> getTradingHistory() {
        return tradingHistory;
    }

    public void setTradingHistory(List<StockPriceHistoryVO> tradingHistory) {
        this.tradingHistory = tradingHistory;
    }

    @Override
    public String toString() {
        return "StockFundamentalHistoryVO{" +
                "tickerSymbol='" + tickerSymbol + '\'' +
                ", currentRatio=" + currentRatio +
                ", marketCap=" + marketCap +
                ", tradingHistory=" + tradingHistory +
                '}';
    }
}
