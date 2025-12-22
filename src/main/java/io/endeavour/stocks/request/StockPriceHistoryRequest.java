package io.endeavour.stocks.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.List;

@Tag(name="StockPriceHistoryRequest", description = "Stock Price History Request")
public class StockPriceHistoryRequest {
    private Long requestID;
    @Schema(name = "tickers",description = "Array of Ticker Symbols",required = true)
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
