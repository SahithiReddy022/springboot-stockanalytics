package io.endeavour.stocks.dao;


import io.endeavour.stocks.vo.StockPriceHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Component
public class StockPriceHistoryDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
}
