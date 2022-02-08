package com.test.task.services.interfaces;

import com.test.task.model.District;
import com.test.task.model.House;
import com.test.task.model.keys.HouseId;
import org.springframework.http.ResponseEntity;

public interface HouseService {
    Iterable<House> getHouses();
    ResponseEntity<House> addHouse(House house);
    void deleteHouse(HouseId houseId);
    ResponseEntity<House> updateHouse(HouseId houseId, House house);
    Iterable<House> getHousesByStreet(String street);
    Iterable<House> getHousesByDistrict(District district);
}
