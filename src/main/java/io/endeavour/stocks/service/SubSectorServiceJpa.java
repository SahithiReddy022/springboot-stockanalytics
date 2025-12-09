package io.endeavour.stocks.service;

import io.endeavour.stocks.entity.stocks.SubSectorEntity;
import io.endeavour.stocks.repository.stocks.SubSectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubSectorServiceJpa {

    @Autowired
    private SubSectorRepository repository;

    public List<SubSectorEntity> getAllSubSectors() {
        return repository.findAll();
    }

    public SubSectorEntity getSubSectorById(Integer id) {
        return repository.findById(id).orElse(null);
    }

}
