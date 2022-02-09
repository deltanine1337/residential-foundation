package com.test.task.model.keys;

import java.io.Serializable;

public class HouseId implements Serializable {
    private String street;
    private Integer houseNumber;

    public HouseId(){}

    public HouseId(String street, Integer houseNumber){
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
