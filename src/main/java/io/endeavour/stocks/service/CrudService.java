package io.endeavour.stocks.service;

import io.endeavour.stocks.entity.crud.PersonEntity;
import io.endeavour.stocks.repository.crud.CrudJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CrudService {

    @Autowired
    private CrudJpaRepository crudRepository;

    public List<PersonEntity> getPersonEntities(){
        return crudRepository.findAll();
    }

    public Optional<PersonEntity> getPersonById(Integer personId) {
        return crudRepository.findById(personId);
    }
}
