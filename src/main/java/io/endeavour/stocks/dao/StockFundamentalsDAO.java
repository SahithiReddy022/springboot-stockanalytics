package io.endeavour.stocks.dao;

import io.endeavour.stocks.vo.StockFundamentalsVO;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Repository
public class StockFundamentalsDAO {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<StockFundamentalsVO> getFundamentals(List<String> tickers) {

        String sql = """
            SELECT 
                ticker_symbol,
                sector_id,
                subsector_id,
                market_cap,
                current_ratio
            FROM endeavour.stock_fundamentals
            WHERE ticker_symbol IN (:tickers)
            """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("tickers", tickers);

        return namedParameterJdbcTemplate.query(sql, params, (rs, i) ->
                new StockFundamentalsVO(
                        rs.getString("ticker_symbol"),
                        rs.getInt("sector_id"),
                        rs.getInt("subsector_id"),
                        rs.getBigDecimal("market_cap"),
                        rs.getDouble("current_ratio")
                )
        );
    }
}
