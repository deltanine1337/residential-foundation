package com.residential.foundation.services;

import com.residential.foundation.model.dto.HouseDTO;
import com.residential.foundation.model.jpa.keys.HouseId;

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
