package com.test.task.model.keys;

import java.io.Serializable;

public class HouseId implements Serializable {
    private String street;
    private int houseNumber;

    public HouseId(String street, int houseNumber){
        this.street = street;
        this.houseNumber = houseNumber;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
