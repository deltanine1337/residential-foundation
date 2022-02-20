package com.test.task.services;

import com.test.task.model.dto.DistrictDTO;

public interface DistrictService {
    Iterable<DistrictDTO> getDistricts();
    DistrictDTO addDistrict(DistrictDTO districtDto);
    void deleteDistrict(Long id);
    DistrictDTO updateDistrict(Long id, DistrictDTO districtDto);
}
