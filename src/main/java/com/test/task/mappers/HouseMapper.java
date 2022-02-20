package com.test.task.mappers;

import com.test.task.model.dto.HouseDto;
import com.test.task.model.jpa.House;

public interface HouseMapper {
   HouseDto toHouseDto(House house);
   House toHouse(HouseDto houseDto);
}
