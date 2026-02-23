package io.endeavour.stocks.Controller;

import io.endeavour.stocks.entity.crud.Person;
import io.endeavour.stocks.exception.PersonException;
import io.endeavour.stocks.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/crud")
public class CrudController {

    @Autowired
    private CrudService crudService;

    @GetMapping("/person")
    public List<Person> getPersons(){
        return crudService.getAllPersons();
    }

    @GetMapping("/person/{personId}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer personId){
        return ResponseEntity.of(crudService.getPerson(personId));
    }

    @PostMapping("/person")
    public Person savePerson(@RequestBody Person person){
       Person savedPerson= crudService.savePerson(person);
       return savedPerson;
    }

    @PutMapping("/person/{personId}")
    public Person updatePerson(@PathVariable Integer personId, @RequestBody Person person){
      // try{
           return crudService.updatePerson(personId,person);
      // }
      // catch (PersonException e){
      //     throw new ResponseStatusException(HttpStatus.NOT_FOUND,
       //            e.getMessage());
      // }
    }

    @DeleteMapping(value = "/person/{personId}")
    public ResponseEntity<String> deletePerson(@PathVariable Integer personId){
        crudService.deletePerson(personId);
        return ResponseEntity.ok("deleted person with id "+ personId);
    }

    @ExceptionHandler({PersonException.class, SQLException.class})
    public ResponseEntity<String> handleException(Exception exception){
        if(exception instanceof PersonException){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.badRequest().body(exception.getMessage());
    }


}
