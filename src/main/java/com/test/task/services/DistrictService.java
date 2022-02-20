package com.test.task.services;

import com.test.task.model.dto.DistrictDto;

public interface DistrictService {
    Iterable<DistrictDto> getDistricts();
    DistrictDto addDistrict(DistrictDto districtDto);
    void deleteDistrict(Long id);
    DistrictDto updateDistrict(Long id, DistrictDto districtDto);
}
