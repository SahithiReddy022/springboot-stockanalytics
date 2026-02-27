package io.endeavour.stocks.vo.remote;

import java.util.List;

public class CumulativeReturnWSInputVO {
    private List<String> tickers;

    public List<String> getTickers() {
        return tickers;
    }

    public void setTickers(List<String> tickers) {
        this.tickers = tickers;
    }

}
