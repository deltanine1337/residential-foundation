package com.test.task.services.impls;

import com.test.task.model.District;
import com.test.task.model.House;
import com.test.task.model.keys.HouseId;
import com.test.task.repos.HouseRepo;
import com.test.task.services.interfaces.HouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HouseServiceImpl implements HouseService {

    private final HouseRepo houseRepo;

    public HouseServiceImpl(HouseRepo houseRepo){
        this.houseRepo = houseRepo;
    }

    public Iterable<House> getHouses() {
        return houseRepo.findAll();
    }

    public ResponseEntity<House> addHouse(House house) {
        houseRepo.save(house);
        return ResponseEntity.ok().body(house);
    }

    public void deleteHouse(HouseId houseId) {
        houseRepo.deleteByHouseId(houseId);
    }

    public ResponseEntity<House> updateHouse(HouseId houseId, House house) {
        House foundHouse = houseRepo.findByHouseId(houseId);
        house.setHouseId(foundHouse.getHouseId());
        houseRepo.save(house);
        return ResponseEntity.ok().body(house);
    }

    public Iterable<House> getHousesByDistrict(String district) {
        return houseRepo.findByDistrict(district);
    }

    public Iterable<House> getHousesByStreet(String street) {
        return houseRepo.findAllByStreet(street);
    }
}
