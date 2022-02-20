package com.residential.foundation.mappers;

import com.residential.foundation.model.dto.HouseDTO;
import com.residential.foundation.model.jpa.House;

public interface HouseMapper {
   HouseDTO toHouseDto(House house);
   House toHouse(HouseDTO houseDto);
}
