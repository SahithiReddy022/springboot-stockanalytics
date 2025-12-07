package io.endeavour.stocks.repository.stocks;

import io.endeavour.stocks.entity.stocks.StockFundamentalsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockFundamentalsRepository extends JpaRepository<StockFundamentalsEntity, String> {
}
