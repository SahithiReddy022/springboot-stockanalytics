package io.endeavour.stocks.Controller;

import io.endeavour.stocks.service.SectorService;
import io.endeavour.stocks.vo.SectorVO;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @GetMapping("/sectors")
    public List<SectorVO> getAllSectors() {
        return sectorService.getAllSectors();
    }

    @GetMapping("/sectors/{sectorId}")
    public SectorVO getSector(@PathVariable int sectorId) {
        SectorVO sector = sectorService.getSectorById(sectorId);
        if (sector == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sector not found");
        }
        return sector;
    }
}
