package io.endeavour.stocks.vo;

import java.math.BigDecimal;

public class CumulativeReturnOutputVO {

    private String tickerSymbol;
    private BigDecimal cumulativeReturn;

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public BigDecimal getCumulativeReturn() {
        return cumulativeReturn;
    }

    public void setCumulativeReturn(BigDecimal cumulativeReturn) {
        this.cumulativeReturn = cumulativeReturn;
    }

    @Override
    public String toString() {
        return "CumulativeReturnVO{" +
                "tickerSymbol='" + tickerSymbol + '\'' +
                ", cumulativeReturn=" + cumulativeReturn +
                '}';
    }
}
