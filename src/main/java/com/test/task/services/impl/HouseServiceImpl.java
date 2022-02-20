package com.test.task.services.impl;

import com.test.task.mappers.HouseMapper;
import com.test.task.model.dto.HouseDTO;
import com.test.task.mappers.impl.HouseMapperImpl;
import com.test.task.model.jpa.House;
import com.test.task.model.jpa.keys.HouseId;
import com.test.task.repos.HouseRepo;
import com.test.task.services.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepo houseRepo;
    private final HouseMapperImpl houseMapper;

    @Override
    public List<HouseDTO> getHouses() {
        return houseRepo.findAll().stream()
                .map(houseMapper::toHouseDto)
                .collect(Collectors.toList());
    }

    @Override
    public HouseDTO addHouse(HouseDTO houseDto) {
        House house = houseRepo.save(houseMapper.toHouse(houseDto));
        return houseMapper.toHouseDto(house);
    }

    @Override
    @Transactional
    public void deleteHouse(HouseId houseId) {
        houseRepo.deleteByHouseId(houseId);
    }

    @Override
    public HouseDTO updateHouse(HouseId houseId, HouseDTO houseDto) {
        House foundHouse = houseRepo.findByHouseId(houseId);
        houseDto.setHouseId(foundHouse.getHouseId());
        House house = houseRepo.save(houseMapper.toHouse(houseDto));
        return houseMapper.toHouseDto(house);
    }

    @Override
    public List<HouseDTO> getHousesByDistrict(String district) {
        return houseRepo.findByDistrict(district.toLowerCase()).stream()
                .map(houseMapper::toHouseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<HouseDTO> getHousesByStreet(String street) {
        return houseRepo.findAllByStreet(street.toLowerCase()).stream()
                .map(houseMapper::toHouseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<HouseDTO> findHouses(String district, String street){
        if (district != null && street == null)
            return getHousesByDistrict(district);
        else if (district == null && street != null)
            return getHousesByStreet(street);
        else return getHouses();
    }
}
