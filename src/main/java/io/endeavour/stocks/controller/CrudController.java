package io.endeavour.stocks.Controller;

import io.endeavour.stocks.entity.crud.PersonEntity;
import io.endeavour.stocks.exception.PersonNotFoundException;
import io.endeavour.stocks.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/crud")
public class CrudController {

    @Autowired
    private CrudService crudService;

    @GetMapping(value = "/person")
    public List<PersonEntity> getPersonEntities(@RequestParam(required = false) Optional<String> firstName){
        if( firstName.isPresent()){
            return crudService.getPersonEntitiesByFirstName(firstName.get());
        }else{
            return crudService.getPersonEntities();
        }
    }

    @PostMapping(value = "/person")
    public PersonEntity savePerson(@RequestBody PersonEntity personEntity){
        return crudService.savePerson(personEntity);
    }

    @PutMapping(value = "/person/{personId}")
    public PersonEntity updatePerson(@PathVariable Integer personId,
                                      @RequestBody  PersonEntity personEntity){
       return crudService.updatePerson(personId, personEntity);

    }


    @GetMapping(value = "/person/{personId}")
    public PersonEntity getPersonById(@PathVariable Integer personId){
        Optional<PersonEntity> personEntityOptional = crudService.getPersonById(personId);

        if(personEntityOptional.isPresent()){
            return personEntityOptional.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping(value = "/person/{personId}")
    public ResponseEntity<String> deletePerson(@PathVariable Integer personId){
        crudService.deletePerson(personId);

        return ResponseEntity.ok("Person with id "+personId+" deleted successfully");

    }

    @ExceptionHandler({PersonNotFoundException.class, Exception.class})
    public ResponseEntity<String> handleExceptions(Exception e){
        if(e instanceof PersonNotFoundException){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.unprocessableEntity().body("Error Processing Request");
    }




}
