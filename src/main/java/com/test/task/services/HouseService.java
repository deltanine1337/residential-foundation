package com.test.task.services;

import com.test.task.model.District;
import com.test.task.model.House;
import com.test.task.model.keys.HouseId;
import org.springframework.http.ResponseEntity;

public interface HouseService {
    Iterable<House> getHouses();
    House addHouse(House house);
    void deleteHouse(HouseId houseId);
    House updateHouse(HouseId houseId, House house);
    Iterable<House> getHousesByStreet(String street);
    Iterable<House> getHousesByDistrict(String district);
    Iterable<House> findHouses(String district, String street);
}
