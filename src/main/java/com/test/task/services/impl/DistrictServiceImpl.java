package com.test.task.services.impl;

import com.test.task.model.dto.DistrictDTO;
import com.test.task.mappers.impl.DistrictMapperImpl;
import com.test.task.model.jpa.District;
import com.test.task.repos.DistrictRepository;
import com.test.task.services.DistrictService;
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
