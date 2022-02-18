package com.test.task.services;

import com.test.task.dto.DistrictDto;
import com.test.task.model.District;
import org.springframework.http.ResponseEntity;

public interface DistrictService {
    Iterable<DistrictDto> getDistricts();
    DistrictDto addDistrict(DistrictDto districtDto);
    void deleteDistrict(Long id);
    DistrictDto updateDistrict(Long id, DistrictDto districtDto);
}
