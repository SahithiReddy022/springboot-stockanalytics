package io.endeavour.stocks.repository.stocks;

import io.endeavour.stocks.entity.stocks.StockPriceHistory;
import io.endeavour.stocks.entity.stocks.StockPriceHistoryPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPriceHistoryRepository extends JpaRepository<StockPriceHistory, StockPriceHistoryPrimaryKey> {
}
