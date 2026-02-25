package io.endeavour.stocks.repository.stocks;

import io.endeavour.stocks.entity.stocks.StockFundamentals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface StockFundamentalsRepository extends JpaRepository<StockFundamentals,String> {
    //spring domain specific lang(dsl)
    public List<StockFundamentals> findAllByMarketCapNotNull();
    public List<StockFundamentals> findAllByMarketCapGreaterThan(BigDecimal marketCap);

    @Query(value = "select sf from StockFundamentals sf where sf.marketCap is not null")
    public List<StockFundamentals> findAllMarketCapByJPQL();

    //using native query
    @Query(nativeQuery = true,value = "select * from endeavour.stock_fundamentals where market_cap is not null")
    public List<StockFundamentals> findAllMarketCapNotNullUsingNativeSQL();
}
