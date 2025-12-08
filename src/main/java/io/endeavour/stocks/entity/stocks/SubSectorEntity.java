package io.endeavour.stocks.entity.stocks;

import javax.persistence.*;

@Entity
@Table(name = "subsector_lookup",schema="endeavour")
public class SubSectorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subsector_id")
    private Integer subsectorId;

    @Column(name = "subsector_name")
    private String subsectorName;

    public SubSectorEntity() {}

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