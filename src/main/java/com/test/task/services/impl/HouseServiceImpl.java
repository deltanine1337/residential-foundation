package com.test.task.services.impl;

import com.test.task.mappers.DistrictMapper;
import com.test.task.mappers.impl.DistrictMapperImpl;
import com.test.task.model.dto.HouseDTO;
import com.test.task.mappers.impl.HouseMapperImpl;
import com.test.task.model.jpa.House;
import com.test.task.model.jpa.keys.HouseId;
import com.test.task.repos.HouseRepository;
import com.test.task.services.HouseService;
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
