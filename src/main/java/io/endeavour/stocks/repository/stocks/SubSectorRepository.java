package io.endeavour.stocks.repository.stocks;

import io.endeavour.stocks.entity.stocks.SubSectorLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;

public interface SubSectorRepository extends JpaRepository<SubSectorLookup, Integer> {
}
