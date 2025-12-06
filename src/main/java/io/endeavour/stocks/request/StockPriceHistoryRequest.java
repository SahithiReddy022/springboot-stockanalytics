package io.endeavour.stocks.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public class StockPriceHistoryRequest {
    private Long requestID;
    private List<String> tickers;
    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate toDate;

    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate fromDate;

    public List<String> getTickers() {
        return tickers;
    }

    public void setTickers(List<String> tickers) {
        this.tickers = tickers;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "StockPriceHistoryRequest{" +
                "tickers=" + tickers +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                '}';
    }
}
