package io.endeavour.stocks.service;

import io.endeavour.stocks.entity.crud.Person;
import io.endeavour.stocks.repository.crud.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CrudService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    public Optional<Person> getPerson(Integer personId){
        return personRepository.findById(personId);
    }
}
