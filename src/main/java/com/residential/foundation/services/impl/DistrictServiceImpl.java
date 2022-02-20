package com.residential.foundation.services.impl;

import com.residential.foundation.model.dto.DistrictDTO;
import com.residential.foundation.model.jpa.District;
import com.residential.foundation.repos.DistrictRepository;
import com.residential.foundation.services.DistrictService;
import com.residential.foundation.mappers.impl.DistrictMapperImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final DistrictMapperImpl districtMapper;

    @Override
    public List<DistrictDTO> getDistricts() {
        return districtRepository.findAll().stream()
                .map(districtMapper::toDistrictDto)
                .collect(Collectors.toList());
    }

    @Override
    public DistrictDTO addDistrict(DistrictDTO districtDto) {
        return districtMapper.toDistrictDto(districtRepository.save(districtMapper.toDistrict(districtDto)));
    }

    @Override
    @Transactional
    public void deleteDistrict(Long id) {
        districtRepository.deleteById(id);
    }

    @Override
    public DistrictDTO updateDistrict(Long id, DistrictDTO districtDto) {
        District foundDistrict = districtRepository.findByDistrictId(id);
        foundDistrict.setDistrictName(districtDto.getDistrictName());
        return districtMapper.toDistrictDto(
                districtRepository.save(foundDistrict)
        );
    }
}
