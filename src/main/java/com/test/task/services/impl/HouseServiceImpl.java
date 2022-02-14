package com.test.task.services.impl;

import com.test.task.model.House;
import com.test.task.model.keys.HouseId;
import com.test.task.repos.HouseRepo;
import com.test.task.services.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepo houseRepo;

    @Override
    public Iterable<House> getHouses() {
        return houseRepo.findAll();
    }

    @Override
    public House addHouse(House house) {
        houseRepo.save(house);
        return house;
    }

    @Override
    @Transactional
    public void deleteHouse(HouseId houseId) {
        houseRepo.deleteByHouseId(houseId);
    }

    @Override
    public House updateHouse(HouseId houseId, House house) {
        House foundHouse = houseRepo.findByHouseId(houseId);
        house.setHouseId(foundHouse.getHouseId());
        houseRepo.save(house);
        return house;
    }

    @Override
    public Iterable<House> getHousesByDistrict(String district) {
        return houseRepo.findByDistrict(district.toLowerCase());
    }

    @Override
    public Iterable<House> getHousesByStreet(String street) {
        return houseRepo.findAllByStreet(street.toLowerCase());
    }

    @Override
    public Iterable<House> findHouses(String district, String street){
        if (district != null && street == null)
            return getHousesByDistrict(district);
        else if (district == null && street != null)
            return getHousesByStreet(street);
        else return getHouses();
    }
}
