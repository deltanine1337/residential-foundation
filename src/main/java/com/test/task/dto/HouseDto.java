package com.test.task.dto;

import com.test.task.model.keys.HouseId;
import lombok.Data;

@Data
public class HouseDto {
    private HouseId houseId;
    private int numberOfApartments;
    private int numberOfFloors;
    private int numberOfEntraces;
    private DistrictDto districtDto;
}
