package io.endeavour.stocks.repository.stocks;

import io.endeavour.stocks.entity.stocks.StockPriceHistoryEntity;
import io.endeavour.stocks.entity.stocks.StockPriceHistoryPrimaryKey;
import io.endeavour.stocks.repository.crud.CrudJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPriceHistoryRepository extends JpaRepository<StockPriceHistoryEntity, StockPriceHistoryPrimaryKey> {
}
