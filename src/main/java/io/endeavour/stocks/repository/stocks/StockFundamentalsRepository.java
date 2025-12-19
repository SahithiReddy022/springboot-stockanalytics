package io.endeavour.stocks.repository.stocks;

import feign.Param;
import io.endeavour.stocks.entity.stocks.StockFundamentalsEntity;
import io.endeavour.stocks.vo.TopStockBySectorVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockFundamentalsRepository extends JpaRepository<StockFundamentalsEntity, String> {

    public List<StockFundamentalsEntity> findByCurrentRatioIsNotNull();

    // JP QL
    @Query(nativeQuery = false, value="SELECT s FROM StockFundamentalsEntity s WHERE s.currentRatio IS NOT NULL")
    List<StockFundamentalsEntity> getStockFundamentalsUsingJPQL();

    // Native SQL
    @Query(nativeQuery = true,value = "SELECT * FROM ENDEAVOUR.STOCK_FUNDAMENTALS " +
            "WHERE CURRENT_RATIO IS NOT NULL")
    List<StockFundamentalsEntity> getStockFundamentalsUsingSql();

    @Query(nativeQuery = true, value = "SELECT * FROM ENDEAVOUR.STOCK_FUNDAMENTALS " +
            "WHERE CURRENT_RATIO IS NOT NULL ORDER BY CURRENT_RATIO DESC LIMIT :number")
    public List<StockFundamentalsEntity> getTopNStockFundamentals(@Param(value = "number") Integer number);

    @Query(nativeQuery = true, name = "StockFundamentals.topStockBySector")
    public List<TopStockBySectorVO> getTopStockBySectorVO();
}
