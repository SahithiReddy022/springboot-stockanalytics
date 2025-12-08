package io.endeavour.stocks.service;

import io.endeavour.stocks.entity.stocks.SectorEntity;
import io.endeavour.stocks.repository.stocks.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorServiceJpa {

    @Autowired
    private SectorRepository repository;

    public List<SectorEntity> getAllSectors() {
        return repository.findAll();
    }

    public SectorEntity getSectorById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
