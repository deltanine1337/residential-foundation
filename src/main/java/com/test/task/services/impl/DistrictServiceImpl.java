package com.test.task.services.impl;

import com.test.task.model.dto.DistrictDTO;
import com.test.task.mappers.impl.DistrictMapperImpl;
import com.test.task.model.jpa.District;
import com.test.task.repos.DistrictRepo;
import com.test.task.services.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepo districtRepo;
    private final DistrictMapperImpl districtMapper;

    @Override
    public List<DistrictDTO> getDistricts() {
        return districtRepo.findAll().stream()
                .map(districtMapper::toDistrictDto)
                .collect(Collectors.toList());
    }

    @Override
    public DistrictDTO addDistrict(DistrictDTO districtDto) {
        District district = districtRepo.save(districtMapper.toDistrict(districtDto));
        return districtMapper.toDistrictDto(district);
    }

    @Override
    @Transactional
    public void deleteDistrict(Long id) {
        districtRepo.deleteById(id);
    }

    @Override
    public DistrictDTO updateDistrict(Long id, DistrictDTO districtDto) {
        District foundDistrict = districtRepo.findByDistrictId(id);
        districtDto.setDistrictId(foundDistrict.getDistrictId());
        District district = districtRepo.save(districtMapper.toDistrict(districtDto));
        return districtMapper.toDistrictDto(district);
    }
}
