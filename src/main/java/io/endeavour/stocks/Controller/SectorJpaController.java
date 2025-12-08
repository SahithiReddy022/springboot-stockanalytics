package io.endeavour.stocks.Controller;

import io.endeavour.stocks.entity.stocks.SectorEntity;
import io.endeavour.stocks.service.SectorServiceJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SectorJpaController {

    @Autowired
    private SectorServiceJpa service;

    // GET /sectors-jpa
    @GetMapping("/sectors-jpa")
    public List<SectorEntity> getAllSectors() {
        return service.getAllSectors();
    }

    // GET /sectors-jpa/{sectorId}
    @GetMapping("/sectors-jpa/{id}")
    public SectorEntity getSectorById(@PathVariable Integer id) {
        return service.getSectorById(id);
    }
}

