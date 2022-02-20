package com.test.task.services;

import com.test.task.model.dto.DistrictDTO;

import java.util.List;

public interface DistrictService {
    List<DistrictDTO> getDistricts();
    DistrictDTO addDistrict(DistrictDTO districtDto);
    void deleteDistrict(Long id);
    DistrictDTO updateDistrict(Long id, DistrictDTO districtDto);
}
