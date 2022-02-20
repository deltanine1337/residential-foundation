package com.test.task.services;

import com.test.task.model.dto.HouseDTO;
import com.test.task.model.jpa.keys.HouseId;

import java.util.List;

public interface HouseService {
    List<HouseDTO> getHouses();
    HouseDTO addHouse(HouseDTO houseDto);
    void deleteHouse(HouseId houseId);
    HouseDTO updateHouse(HouseId houseId, HouseDTO houseDto);
    List<HouseDTO> getHousesByStreet(String street);
    List<HouseDTO> getHousesByDistrict(String district);
    List<HouseDTO> findHouses(String district, String street);
}
