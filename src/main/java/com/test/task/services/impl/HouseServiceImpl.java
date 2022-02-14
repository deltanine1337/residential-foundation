package com.test.task.services.impl;

import com.test.task.model.House;
import com.test.task.model.keys.HouseId;
import com.test.task.repos.HouseRepo;
import com.test.task.services.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepo houseRepo;

    @Override
    public Iterable<House> getHouses() {
        return houseRepo.findAll();
    }

    @Override
    public ResponseEntity<House> addHouse(House house) {
        houseRepo.save(house);
        return ResponseEntity.ok().body(house);
    }

    @Override
    public void deleteHouse(HouseId houseId) {
        houseRepo.deleteByHouseId(houseId);
    }

    @Override
    public ResponseEntity<House> updateHouse(HouseId houseId, House house) {
        House foundHouse = houseRepo.findByHouseId(houseId);
        house.setHouseId(foundHouse.getHouseId());
        houseRepo.save(house);
        return ResponseEntity.ok().body(house);
    }

    @Override
    public Iterable<House> getHousesByDistrict(String district) {
        return houseRepo.findByDistrict(district);
    }

    @Override
    public Iterable<House> getHousesByStreet(String street) {
        return houseRepo.findAllByStreet(street);
    }

    @Override
    public Iterable<House> findHouses(String district, String street){
        if (district == null && street == null)
            return getHouses();
        else if (district != null && street == null)
            return getHousesByDistrict(district);
        else if (district == null && street != null)
            return getHousesByStreet(street);
        else return null;
    }
}
