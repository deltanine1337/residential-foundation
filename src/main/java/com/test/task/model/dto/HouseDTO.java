package com.test.task.model.dto;

import com.test.task.model.jpa.keys.HouseId;
import lombok.Data;

@Data
public class HouseDTO {
    private HouseId houseId;
    private int numberOfApartments;
    private int numberOfFloors;
    private int numberOfEntraces;
    private DistrictDTO districtDto;
}
