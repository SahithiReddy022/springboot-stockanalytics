package io.endeavour.stocks.entity.stocks;

import javax.persistence.*;

@Entity
@Table(name = "sector_lookup",schema="endeavour")
public class SectorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sector_id")
    private Integer sectorId;

    @Column(name = "sector_name")
    private String sectorName;

    public SectorEntity() {
    }

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