package com.test.task.services.interfaces;

import com.test.task.model.District;
import org.springframework.http.ResponseEntity;

public interface DistrictService {
    Iterable<District> getDistricts();
    ResponseEntity<District> addDistrict(District district);
    void deleteDistrict(Long id);
    ResponseEntity<District> updateDistrict(Long id, District district);
}
