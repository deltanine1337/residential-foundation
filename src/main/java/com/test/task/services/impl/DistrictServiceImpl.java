package com.test.task.services.impl;

import com.test.task.model.District;
import com.test.task.repos.DistrictRepo;
import com.test.task.services.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepo districtRepo;

    @Override
    public Iterable<District> getDistricts() {
        return districtRepo.findAll();
    }

    @Override
    public ResponseEntity<District> addDistrict(District district) {
        districtRepo.save(district);
        return ResponseEntity.ok().body(district);
    }

    @Override
    public void deleteDistrict(Long id) {
        districtRepo.deleteById(id);
    }

    @Override
    public ResponseEntity<District> updateDistrict(Long id, District district) {
        District foundDistrict = districtRepo.findByDistrictId(id);
        district.setDistrictId(foundDistrict.getDistrictId());
        districtRepo.save(district);
        return ResponseEntity.ok().body(district);
    }
}
