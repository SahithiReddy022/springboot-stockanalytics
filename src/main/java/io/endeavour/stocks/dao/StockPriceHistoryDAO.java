package io.endeavour.stocks.dao;


import io.endeavour.stocks.vo.StockPriceHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
public class StockPriceHistoryDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<StockPriceHistoryVO> getSingleStockPriceHistory(String tickerSymbol){
        String sql= """
                SELECT
                TICKER_SYMBOL,
                TRADING_DATE,
                OPEN_PRICE,
                CLOSE_PRICE,
                VOLUME
                FROM ENDEAVOUR.STOCKS_PRICE_HISTORY
                WHERE TICKER_SYMBOL=?
                LIMIT 10
                """;
        Object[] inputs=new Object[1];
        inputs[0]= tickerSymbol;
        List<StockPriceHistoryVO> stockPriceHistoryVOList=jdbcTemplate.query(sql, inputs, new RowMapper<StockPriceHistoryVO>() {
            @Override
            public StockPriceHistoryVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                StockPriceHistoryVO stockPriceHistoryVO=new StockPriceHistoryVO();
                stockPriceHistoryVO.setTickerSymbol(rs.getString("TICKER_SYMBOL"));
                stockPriceHistoryVO.setTradingDate(rs.getDate("TRADING_DATE").toLocalDate());
                stockPriceHistoryVO.setOpenPrice(rs.getBigDecimal("OPEN_PRICE"));
                stockPriceHistoryVO.setClosePrice(rs.getBigDecimal("CLOSE_PRICE"));
                stockPriceHistoryVO.setVolume(rs.getDouble("VOLUME"));
                return stockPriceHistoryVO;
            }
        });
        return stockPriceHistoryVOList;
    }

    public List<StockPriceHistoryVO> getStockPriceHistory(String tickerSymbol, LocalDate fromDate, LocalDate toDate){
        String sql= """
                SELECT TICKER_SYMBOL, TRADING_DATE, OPEN_PRICE, CLOSE_PRICE, VOLUME
                FROM ENDEAVOUR.STOCKS_PRICE_HISTORY
                WHERE TICKER_SYMBOL=:tickerSymbol
                AND TRADING_DATE BETWEEN :fromDate AND :toDate
                """;
        MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource();
        mapSqlParameterSource.addValue("fromDate",fromDate);
        mapSqlParameterSource.addValue("tickerSymbol",tickerSymbol);
        mapSqlParameterSource.addValue("toDate",toDate);
        List<StockPriceHistoryVO> stockPriceHistoryVOList=namedParameterJdbcTemplate.query(sql,
                mapSqlParameterSource, (rs, rowNum) -> {
                    StockPriceHistoryVO stockPriceHistoryVO=new StockPriceHistoryVO();
                    stockPriceHistoryVO.setTickerSymbol(rs.getString("TICKER_SYMBOL"));
                    stockPriceHistoryVO.setTradingDate(rs.getDate("TRADING_DATE").toLocalDate());
                    stockPriceHistoryVO.setOpenPrice(rs.getBigDecimal("OPEN_PRICE"));
                    stockPriceHistoryVO.setClosePrice(rs.getBigDecimal("CLOSE_PRICE"));
                    stockPriceHistoryVO.setVolume(rs.getDouble("VOLUME"));
                    return stockPriceHistoryVO;
                });
        return stockPriceHistoryVOList;

    }

    public List<StockPriceHistoryVO> getStockPriceHistoryForDateRange(List<String> tickerSymbols, LocalDate fromDate, LocalDate toDate){
        String sql= """
                SELECT TICKER_SYMBOL, TRADING_DATE, OPEN_PRICE, CLOSE_PRICE, VOLUME
                FROM ENDEAVOUR.STOCKS_PRICE_HISTORY
                WHERE TICKER_SYMBOL IN (:tickerSymbols)
                AND TRADING_DATE BETWEEN :fromDate AND :toDate
                """;
        MapSqlParameterSource mapSqlParameterSource=new MapSqlParameterSource();
        mapSqlParameterSource.addValue("fromDate",fromDate);
        mapSqlParameterSource.addValue("tickerSymbols",tickerSymbols);
        mapSqlParameterSource.addValue("toDate",toDate);
        List<StockPriceHistoryVO> stockPriceHistoryVOList=namedParameterJdbcTemplate.query(sql,
                mapSqlParameterSource, (rs, rowNum) -> {
                    StockPriceHistoryVO stockPriceHistoryVO=new StockPriceHistoryVO();
                    stockPriceHistoryVO.setTickerSymbol(rs.getString("TICKER_SYMBOL"));
                    stockPriceHistoryVO.setTradingDate(rs.getDate("TRADING_DATE").toLocalDate());
                    stockPriceHistoryVO.setOpenPrice(rs.getBigDecimal("OPEN_PRICE"));
                    stockPriceHistoryVO.setClosePrice(rs.getBigDecimal("CLOSE_PRICE"));
                    stockPriceHistoryVO.setVolume(rs.getDouble("VOLUME"));
                    return stockPriceHistoryVO;
                });
        return stockPriceHistoryVOList;

    }
}
