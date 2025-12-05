package io.endeavour.stocks.exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException() {
    }

    public StockNotFoundException(String message) {
        super(message);
    }

    public StockNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
