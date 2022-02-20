package com.test.task.mappers.impl;

import com.test.task.model.dto.HouseDTO;
import com.test.task.mappers.HouseMapper;
import com.test.task.model.jpa.House;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HouseMapperImpl implements HouseMapper {

    private final DistrictMapperImpl districtMapper;

    @Override
    public HouseDTO toHouseDto(House house) {
        HouseDTO houseDto = new HouseDTO();
        houseDto.setHouseId(house.getHouseId());
        houseDto.setNumberOfApartments(house.getNumberOfApartments());
        houseDto.setNumberOfEntraces(house.getNumberOfEntraces());
        houseDto.setNumberOfFloors(house.getNumberOfFloors());
        houseDto.setDistrictDto(districtMapper.toDistrictDto(house.getDistrict()));
        return houseDto;
    }

    @Override
    public House toHouse(HouseDTO houseDto) {
        House house = new House();
        house.setHouseId(houseDto.getHouseId());
        house.setNumberOfApartments(houseDto.getNumberOfApartments());
        house.setNumberOfEntraces(houseDto.getNumberOfEntraces());
        house.setNumberOfFloors(houseDto.getNumberOfFloors());
        house.setDistrict(districtMapper.toDistrict(houseDto.getDistrictDto()));
        return house;
    }
}
