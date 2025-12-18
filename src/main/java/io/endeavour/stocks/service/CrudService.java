package io.endeavour.stocks.service;

import io.endeavour.stocks.entity.crud.AddressEntity;
import io.endeavour.stocks.entity.crud.PersonEntity;
import io.endeavour.stocks.exception.PersonNotFoundException;
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

    public List<PersonEntity> getPersonEntitiesByFirstName(String firstName){
        return crudRepository.findByFirstNameStartsWith(firstName);
    }

    public Optional<PersonEntity> getPersonById(Integer personId) {
        return crudRepository.findById(personId);
    }

    public PersonEntity savePerson(PersonEntity personEntity) {
        List<AddressEntity> addressEntityList = personEntity.getAddressEntityList();
        addressEntityList.stream()
                .forEach(addressEntity -> addressEntity.setPersonEntity(personEntity));

        PersonEntity savedPersonEntity = crudRepository.save(personEntity);
        return savedPersonEntity;
    }

    public PersonEntity updatePerson(Integer personId, PersonEntity personEntity) {
        boolean personExists = crudRepository.existsById(personId);
        if(!personExists){
            throw new PersonNotFoundException();
        }
        return savePerson(personEntity);

    }

    public void deletePerson(Integer personId) {
        boolean personExists = crudRepository.existsById(personId);
        if(!personExists){
            throw new PersonNotFoundException();
        }
        crudRepository.deleteById(personId);
    }
}

