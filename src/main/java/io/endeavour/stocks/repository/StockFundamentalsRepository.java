package io.endeavour.stocks.repository;

import io.endeavour.stocks.entity.stocks.StockFundamentals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockFundamentalsRepository extends JpaRepository<StockFundamentals,String> {
}
