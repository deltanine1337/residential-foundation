package com.test.task.services.impl;

import com.test.task.model.dto.DistrictDto;
import com.test.task.mappers.impl.DistrictMapperImpl;
import com.test.task.repos.DistrictRepo;
import com.test.task.services.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepo districtRepo;
    private final DistrictMapperImpl districtMapper;

    @Override
    public Iterable<DistrictDto> getDistricts() {
        return districtRepo.findAll().stream()
                .map(districtMapper::toDistrictDto)
                .collect(Collectors.toList());
    }

    @Override
    public DistrictDto addDistrict(DistrictDto districtDto) {
        districtRepo.save(districtMapper.toDistrict(districtDto));
        return districtDto;
    }

    @Override
    @Transactional
    public void deleteDistrict(Long id) {
        districtRepo.deleteById(id);
    }

    @Override
    public DistrictDto updateDistrict(Long id, DistrictDto districtDto) {
        DistrictDto foundDistrict = districtMapper.toDistrictDto(districtRepo.findByDistrictId(id));
        districtDto.setDistrictId(foundDistrict.getDistrictId());
        districtRepo.save(districtMapper.toDistrict(districtDto));
        return districtDto;
    }
}
