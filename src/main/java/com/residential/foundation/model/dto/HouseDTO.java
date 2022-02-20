package com.residential.foundation.model.dto;

import com.residential.foundation.model.jpa.keys.HouseId;
import lombok.Data;

@Data
public class HouseDTO {
    private HouseId houseId;
    private int numberOfApartments;
    private int numberOfFloors;
    private int numberOfEntraces;
    private DistrictDTO districtDto;
}
