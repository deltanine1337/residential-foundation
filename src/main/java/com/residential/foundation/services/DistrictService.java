package com.residential.foundation.services;

import com.residential.foundation.model.dto.DistrictDTO;

import java.util.List;

public interface DistrictService {
    List<DistrictDTO> getDistricts();
    DistrictDTO addDistrict(DistrictDTO districtDto);
    void deleteDistrict(Long id);
    DistrictDTO updateDistrict(Long id, DistrictDTO districtDto);
}
