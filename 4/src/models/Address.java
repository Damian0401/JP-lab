package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class Address {
    private int id;
    private String postCode;
    private String street;
    private int houseNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    @JsonIgnore
    public String getFullName(){
        return street + " " + houseNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return getHouseNumber() == address.getHouseNumber() && Objects.equals(getPostCode(), address.getPostCode()) && Objects.equals(getStreet(), address.getStreet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPostCode(), getStreet(), getHouseNumber());
    }
}
