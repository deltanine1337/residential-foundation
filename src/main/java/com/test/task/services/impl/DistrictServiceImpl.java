package com.test.task.services.impl;

import com.test.task.model.District;
import com.test.task.repos.DistrictRepo;
import com.test.task.services.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepo districtRepo;

    @Override
    public Iterable<District> getDistricts() {
        return districtRepo.findAll();
    }

    @Override
    public District addDistrict(District district) {
        districtRepo.save(district);
        return district;
    }

    @Override
    @Transactional
    public void deleteDistrict(Long id) {
        districtRepo.deleteById(id);
    }

    @Override
    public District updateDistrict(Long id, District district) {
        District foundDistrict = districtRepo.findByDistrictId(id);
        district.setDistrictId(foundDistrict.getDistrictId());
        districtRepo.save(district);
        return district;
    }
}
