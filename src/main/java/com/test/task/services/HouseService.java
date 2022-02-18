package com.test.task.services;

import com.test.task.dto.HouseDto;
import com.test.task.model.keys.HouseId;

public interface HouseService {
    Iterable<HouseDto> getHouses();
    HouseDto addHouse(HouseDto houseDto);
    void deleteHouse(HouseId houseId);
    HouseDto updateHouse(HouseId houseId, HouseDto houseDto);
    Iterable<HouseDto> getHousesByStreet(String street);
    Iterable<HouseDto> getHousesByDistrict(String district);
    Iterable<HouseDto> findHouses(String district, String street);
}
