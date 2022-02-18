package com.test.task.services.impl;

import com.test.task.dto.HouseDto;
import com.test.task.mappers.impl.HouseMapperImpl;
import com.test.task.model.House;
import com.test.task.model.keys.HouseId;
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
    public Iterable<HouseDto> getHouses() {
        return houseRepo.findAll().stream()
                .map(houseMapper::toHouseDto)
                .collect(Collectors.toList());
    }

    @Override
    public HouseDto addHouse(HouseDto houseDto) {
        houseRepo.save(houseMapper.toHouse(houseDto));
        return houseDto;
    }

    @Override
    @Transactional
    public void deleteHouse(HouseId houseId) {
        houseRepo.deleteByHouseId(houseId);
    }

    @Override
    public HouseDto updateHouse(HouseId houseId, HouseDto houseDto) {
        HouseDto foundHouse = houseMapper.toHouseDto(houseRepo.findByHouseId(houseId));
        houseDto.setHouseId(foundHouse.getHouseId());
        houseRepo.save(houseMapper.toHouse(houseDto));
        return houseDto;
    }

    @Override
    public Iterable<HouseDto> getHousesByDistrict(String district) {
        return houseRepo.findByDistrict(district.toLowerCase()).stream()
                .map(houseMapper::toHouseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<HouseDto> getHousesByStreet(String street) {
        return houseRepo.findAllByStreet(street.toLowerCase()).stream()
                .map(houseMapper::toHouseDto)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<HouseDto> findHouses(String district, String street){
        if (district != null && street == null)
            return getHousesByDistrict(district);
        else if (district == null && street != null)
            return getHousesByStreet(street);
        else return getHouses();
    }
}
