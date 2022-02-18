package com.test.task.mappers;

import com.test.task.dto.HouseDto;
import com.test.task.model.House;

public interface HouseMapper {
   HouseDto toHouseDto(House house);
   House toHouse(HouseDto houseDto);
}
