package io.endeavour.stocks.dao;

import io.endeavour.stocks.vo.StockPriceHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Component
public class StockPriceHistoryDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public StockPriceHistoryVO getDummyStockPriceHistoryVO() {
        StockPriceHistoryVO stockPriceHistoryVO = new StockPriceHistoryVO(
                "AAPL",
                LocalDate.now(),
                new BigDecimal("100"),
                new BigDecimal("200"),
                10000d
        );
        return stockPriceHistoryVO;
    }

    public List<StockPriceHistoryVO> getSingleStockPriceHistory(String ticker){
        String query= """
                SELECT 
                    TICKER_SYMBOL,
                    TRADING_DATE,
                    OPEN_PRICE,
                    CLOSE_PRICE,
                    VOLUME
                FROM ENDEAVOUR.STOCKS_PRICE_HISTORY
                WHERE TICKER_SYMBOL = ? and TRADING_DATE > current_date - 180
                """;
        Object[] inputs = new Object[1];
        inputs[0] = ticker;
        List<StockPriceHistoryVO> stockPriceHistoryVOList =
//                jdbcTemplate.query(query, inputs, new RowMapper<StockPriceHistoryVO>() {
//                    @Override
//                    public StockPriceHistoryVO mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        String tickerSymbol = rs.getString("TICKER_SYMBOL");
//                        LocalDate tradingDate = rs.getDate("TRADING_DATE").toLocalDate();
//                        BigDecimal openPrice = rs.getBigDecimal("OPEN_PRICE");
//                        BigDecimal closePrice = rs.getBigDecimal("CLOSE_PRICE");
//                        double volume = rs.getDouble("VOLUME");
//                        StockPriceHistoryVO stockPriceHistoryVO = new StockPriceHistoryVO(
//                                tickerSymbol,
//                                tradingDate,
//                                openPrice,
//                                closePrice,
//                                volume
//                        );
//
//                        return stockPriceHistoryVO;
//                    }
                     jdbcTemplate.query(query, inputs, (rs, rowNum) -> {
                         String tickerSymbol = rs.getString("TICKER_SYMBOL");
                         LocalDate tradingDate = rs.getDate("TRADING_DATE").toLocalDate();
                         BigDecimal openPrice = rs.getBigDecimal("OPEN_PRICE");
                         BigDecimal closePrice = rs.getBigDecimal("CLOSE_PRICE");
                         double volume = rs.getDouble("VOLUME");
                         StockPriceHistoryVO stockPriceHistoryVO = new StockPriceHistoryVO(
                                 tickerSymbol,
                                 tradingDate,
                                 openPrice,
                                 closePrice,
                                 volume
                         );

                         return stockPriceHistoryVO;
                     });
        return stockPriceHistoryVOList;

    }

    public List<StockPriceHistoryVO> getStockPriceHistoryForTickersForDateRang(List<String> tickerList,
                                                                               LocalDate fromDate,
                                                                               LocalDate toDate){
        String query= """
                SELECT 
                    TICKER_SYMBOL,
                    TRADING_DATE,
                    OPEN_PRICE,
                    CLOSE_PRICE,
                    VOLUME
                FROM ENDEAVOUR.STOCKS_PRICE_HISTORY
                WHERE TICKER_SYMBOL in  (:tickers) 
                    and TRADING_DATE between :fromDate and :toDate
                
                """;
        MapSqlParameterSource inputs = new MapSqlParameterSource();
        inputs.addValue("fromDate", fromDate);
        inputs.addValue("tickers", tickerList);
        inputs.addValue("toDate", toDate);
        List<StockPriceHistoryVO> stockPriceHistoryVOList =
//                jdbcTemplate.query(query, inputs, new RowMapper<StockPriceHistoryVO>() {
//                    @Override
//                    public StockPriceHistoryVO mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        String tickerSymbol = rs.getString("TICKER_SYMBOL");
//                        LocalDate tradingDate = rs.getDate("TRADING_DATE").toLocalDate();
//                        BigDecimal openPrice = rs.getBigDecimal("OPEN_PRICE");
//                        BigDecimal closePrice = rs.getBigDecimal("CLOSE_PRICE");
//                        double volume = rs.getDouble("VOLUME");
//                        StockPriceHistoryVO stockPriceHistoryVO = new StockPriceHistoryVO(
//                                tickerSymbol,
//                                tradingDate,
//                                openPrice,
//                                closePrice,
//                                volume
//                        );
//
//                        return stockPriceHistoryVO;
//                    }
                namedParameterJdbcTemplate.query(query, inputs, (rs, rowNum) -> {
                    String tickerSymbol = rs.getString("TICKER_SYMBOL");
                    LocalDate tradingDate = rs.getDate("TRADING_DATE").toLocalDate();
                    BigDecimal openPrice = rs.getBigDecimal("OPEN_PRICE");
                    BigDecimal closePrice = rs.getBigDecimal("CLOSE_PRICE");
                    double volume = rs.getDouble("VOLUME");
                    StockPriceHistoryVO stockPriceHistoryVO = new StockPriceHistoryVO(
                            tickerSymbol,
                            tradingDate,
                            openPrice,
                            closePrice,
                            volume
                    );

                    return stockPriceHistoryVO;
                });
        return stockPriceHistoryVOList;

    }





}
