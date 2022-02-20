package com.test.task.services;

import com.test.task.model.dto.HouseDTO;
import com.test.task.model.jpa.keys.HouseId;

public interface HouseService {
    Iterable<HouseDTO> getHouses();
    HouseDTO addHouse(HouseDTO houseDto);
    void deleteHouse(HouseId houseId);
    HouseDTO updateHouse(HouseId houseId, HouseDTO houseDto);
    Iterable<HouseDTO> getHousesByStreet(String street);
    Iterable<HouseDTO> getHousesByDistrict(String district);
    Iterable<HouseDTO> findHouses(String district, String street);
}
