package io.endeavour.stocks.service;

import io.endeavour.stocks.entity.crud.Person;
import io.endeavour.stocks.exception.PersonException;
import io.endeavour.stocks.repository.crud.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
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

    public Person savePerson(Person person){
        if(person.getAddressList()!=null) {
            person.getAddressList()
                    .stream()
                    .forEach(address -> address.setPerson(person));
        }
        Person savedPerson=personRepository.save(person);
        return savedPerson;
    }

    public Person updatePerson(Integer personId, Person person){
        boolean personDoesExist= personRepository.existsById(personId);
        if(personDoesExist){
            return savePerson(person);
        }
        throw new PersonException("person does not exists"+ personId);
    }

    public Person deletePerson(Integer personId){
        boolean personDoesExist= personRepository.existsById(personId);
        if(personDoesExist){
            personRepository.deleteById(personId);
        }
        throw new PersonException("person does not exists"+ personId);
    }
}
