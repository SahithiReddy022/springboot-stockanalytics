package io.endeavour.stocks.repository.stocks;

import io.endeavour.stocks.entity.stocks.SectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository extends JpaRepository<SectorEntity, Integer> {
}
