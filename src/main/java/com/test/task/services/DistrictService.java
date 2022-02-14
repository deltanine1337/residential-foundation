package com.test.task.services;

import com.test.task.model.District;
import org.springframework.http.ResponseEntity;

public interface DistrictService {
    Iterable<District> getDistricts();
    District addDistrict(District district);
    void deleteDistrict(Long id);
    District updateDistrict(Long id, District district);
}
