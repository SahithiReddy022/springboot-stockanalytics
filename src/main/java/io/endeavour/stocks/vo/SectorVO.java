package io.endeavour.stocks.vo;

public class SectorVO {
    private Integer sectorId;
    private String sectorName;

    public SectorVO(Integer sectorId, String sectorName) {
        this.sectorId = sectorId;
        this.sectorName = sectorName;
    }

    public Integer getSectorId() { return sectorId; }
    public String getSectorName() { return sectorName; }
}
