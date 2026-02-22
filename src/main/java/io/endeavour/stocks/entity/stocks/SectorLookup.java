package io.endeavour.stocks.entity.stocks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="SECTOR_LOOKUP", schema = "ENDEAVOUR")
public class SectorLookup {
    @Id
    @Column(name = "SECTOR_ID")
    private Integer sectorId;

    @Column(name = "SECTOR_NAME")
    private String sectorName;

    public Integer getSectorId() {
        return sectorId;
    }

    public void setSectorId(Integer sectorId) {
        this.sectorId = sectorId;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

}
