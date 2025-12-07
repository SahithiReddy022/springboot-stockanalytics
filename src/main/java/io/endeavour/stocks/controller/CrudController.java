package io.endeavour.stocks.controller;

import io.endeavour.stocks.entity.crud.PersonEntity;
import io.endeavour.stocks.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/crud")
public class CrudController {

    @Autowired
    private CrudService crudService;

    @GetMapping(value = "/person")
    public List<PersonEntity> getPersonEntities(){
        return crudService.getPersonEntities();
    }
    @GetMapping(value = "/person/{personId}")
    public PersonEntity getPersonById(@PathVariable Integer personId){
        Optional<PersonEntity> personEntityOptional = crudService.getPersonById(personId);

        if(personEntityOptional.isPresent()){
            return personEntityOptional.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }
}
