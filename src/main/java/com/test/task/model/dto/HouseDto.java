package com.test.task.model.dto;

import com.test.task.model.jpa.keys.HouseId;
import lombok.Data;

@Data
public class HouseDto {
    private HouseId houseId;
    private int numberOfApartments;
    private int numberOfFloors;
    private int numberOfEntraces;
    private DistrictDto districtDto;
}
