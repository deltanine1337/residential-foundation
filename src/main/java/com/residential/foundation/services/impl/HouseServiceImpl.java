package com.residential.foundation.services.impl;

import com.residential.foundation.model.dto.HouseDTO;
import com.residential.foundation.model.jpa.House;
import com.residential.foundation.model.jpa.keys.HouseId;
import com.residential.foundation.repos.HouseRepository;
import com.residential.foundation.mappers.impl.DistrictMapperImpl;
import com.residential.foundation.mappers.impl.HouseMapperImpl;
import com.residential.foundation.services.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;
    private final HouseMapperImpl houseMapper;
    private final DistrictMapperImpl districtMapper;

    @Override
    public List<HouseDTO> getHouses() {
        return houseRepository.findAll().stream()
                .map(houseMapper::toHouseDto)
                .collect(Collectors.toList());
    }

    @Override
    public HouseDTO addHouse(HouseDTO houseDto) {
        return houseMapper.toHouseDto(houseRepository.save(houseMapper.toHouse(houseDto)));
    }

    @Override
    @Transactional
    public void deleteHouse(HouseId houseId) {
        houseRepository.deleteByHouseId(houseId);
    }

    @Override
    public HouseDTO updateHouse(HouseId houseId, HouseDTO houseDto) {
        House foundHouse = houseRepository.findByHouseId(houseId);
        foundHouse.setNumberOfApartments(houseDto.getNumberOfApartments());
        foundHouse.setNumberOfEntraces(houseDto.getNumberOfEntraces());
        foundHouse.setNumberOfFloors(houseDto.getNumberOfFloors());
        foundHouse.setDistrict(districtMapper.toDistrict(houseDto.getDistrictDto()));
        return houseMapper.toHouseDto(
                houseRepository.save(foundHouse)
        );
    }

    @Override
    public List<HouseDTO> getHousesByDistrict(String district) {
        return houseRepository.findByDistrict(district.toLowerCase()).stream()
                .map(houseMapper::toHouseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<HouseDTO> getHousesByStreet(String street) {
        return houseRepository.findAllByStreet(street.toLowerCase()).stream()
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
