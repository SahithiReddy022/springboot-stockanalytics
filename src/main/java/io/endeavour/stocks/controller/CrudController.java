package io.endeavour.stocks.Controller;

import io.endeavour.stocks.entity.crud.Person;
import io.endeavour.stocks.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
