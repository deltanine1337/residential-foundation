package com.test.task.services.impls;

import com.test.task.model.District;
import com.test.task.repos.DistrictRepo;
import com.test.task.services.interfaces.DistrictService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepo districtRepo;

    private DistrictServiceImpl(DistrictRepo districtRepo){
        this.districtRepo = districtRepo;
    }

    public Iterable<District> getDistricts() {
        return districtRepo.findAll();
    }

    public ResponseEntity<District> addDistrict(District district) {
        districtRepo.save(district);
        return ResponseEntity.ok().body(district);
    }

    public void deleteDistrict(Long id) {
        districtRepo.deleteById(id);
    }

    public ResponseEntity<District> updateDistrict(Long id, District district) {
        District foundDistrict = districtRepo.findByDistrictId(id);
        district.setDistrictId(foundDistrict.getDistrictId());
        districtRepo.save(district);
        return ResponseEntity.ok().body(district);
    }
}
