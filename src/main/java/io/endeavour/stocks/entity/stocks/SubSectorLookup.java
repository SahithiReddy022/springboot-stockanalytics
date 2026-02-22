package io.endeavour.stocks.entity.stocks;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "subsector_lookup",schema="endeavour")
public class SubSectorLookup {
    @Id
    @Column(name = "SUBSECTOR_ID")
    private Integer subsectorId;

    @Column(name = "SUBSECTOR_NAME")
    private String subsectorName;

    public SubSectorLookup() {}

    public Integer getSubsectorId() {
        return subsectorId;
    }

    public void setSubsectorId(Integer subsectorId) {
        this.subsectorId = subsectorId;
    }

    public String getSubsectorName() {
        return subsectorName;
    }

    public void setSubsectorName(String subsectorName) {
        this.subsectorName = subsectorName;
    }

}
