package io.endeavour.stocks.repository.crud;

import io.endeavour.stocks.entity.crud.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrudJpaRepository extends JpaRepository<PersonEntity, Integer> {

    //Spring derived methods
    public List<PersonEntity> findByFirstNameStartsWith(String firstName);
    public List<PersonEntity> findByFirstNameOrLastName(String firstName, String lastName);
}

