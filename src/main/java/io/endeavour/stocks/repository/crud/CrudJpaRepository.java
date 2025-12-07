package io.endeavour.stocks.repository.crud;

import io.endeavour.stocks.entity.crud.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudJpaRepository extends JpaRepository<PersonEntity, Integer> {
}
