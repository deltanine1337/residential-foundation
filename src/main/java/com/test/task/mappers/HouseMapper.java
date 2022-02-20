package com.test.task.mappers;

import com.test.task.model.dto.HouseDTO;
import com.test.task.model.jpa.House;

public interface HouseMapper {
   HouseDTO toHouseDto(House house);
   House toHouse(HouseDTO houseDto);
}
