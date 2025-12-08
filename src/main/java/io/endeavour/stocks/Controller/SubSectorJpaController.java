package io.endeavour.stocks.Controller;

import io.endeavour.stocks.entity.stocks.SubSectorEntity;
import io.endeavour.stocks.service.SubSectorServiceJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubSectorJpaController {

    @Autowired
    private SubSectorServiceJpa service;

    // GET /subsectors-jpa
    @GetMapping("/subsectors-jpa")
    public List<SubSectorEntity> getAllSubSectors() {
        return service.getAllSubSectors();
    }

    // GET /subsectors-jpa/{id}
    @GetMapping("/subsectors-jpa/{id}")
    public SubSectorEntity getSubSectorById(@PathVariable Integer id) {
        return service.getSubSectorById(id);
    }
}
