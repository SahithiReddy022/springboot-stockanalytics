package io.endeavour.stocks.entity.crud;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS",schema = "ENDEAVOUR_TEST_AREA")
public class Address {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ADDRESS_ID")
    Integer addressId;
    @Column(name = "LINE1")
    String addressLineOne;
    @Column(name = "LINE2")
    String addressLineTwo;
    @Column(name = "CITY")
    String city;
    @Column(name = "STATE")
    String state;
    @Column(name = "ZIP_CODE")
    Integer zip;
@JsonIgnore
@ManyToOne
    @JoinColumn(name="PERSON_ID")
    Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddressLineOne() {
        return addressLineOne;
    }

    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }

    public String getAddressLineTwo() {
        return addressLineTwo;
    }

    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }
}
