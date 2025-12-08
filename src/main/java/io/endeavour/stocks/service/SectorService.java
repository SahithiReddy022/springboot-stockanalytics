package io.endeavour.stocks.service;

import io.endeavour.stocks.dao.SectorDAO;
import io.endeavour.stocks.vo.SectorVO;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class SectorService {

    @Autowired
    private SectorDAO sectorDAO;

    public List<SectorVO> getAllSectors() {
        return sectorDAO.getAllSectors();
    }

    public SectorVO getSectorById(int id) {
        return sectorDAO.getSectorById(id);
    }
}
