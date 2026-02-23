package io.endeavour.stocks.entity.crud;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "PERSON",schema = "ENDEAVOUR_TEST_AREA")
public class Person {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "PERSON_ID")
    Integer personId;
@Column(name = "FIRST_NAME")
    String firstName;
@Column(name = "LAST_NAME")
    String lastName;
@Column(name = "dob")
    LocalDate dob;

@OneToMany(mappedBy = "person",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
List<Address> addressList;

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                '}';
    }
}
