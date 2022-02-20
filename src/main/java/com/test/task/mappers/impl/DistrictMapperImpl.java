package com.test.task.mappers.impl;

import com.test.task.model.dto.DistrictDto;
import com.test.task.mappers.DistrictMapper;
import com.test.task.model.jpa.District;
import org.springframework.stereotype.Service;

@Service
public class DistrictMapperImpl implements DistrictMapper {

    @Override
    public DistrictDto toDistrictDto(District district) {
        DistrictDto districtDto = new DistrictDto();
        districtDto.setDistrictId(district.getDistrictId());
        districtDto.setDistrictName(district.getDistrictName());
        return districtDto;
    }

    @Override
    public District toDistrict(DistrictDto districtDto) {
        District district = new District();
        district.setDistrictId(districtDto.getDistrictId());
        district.setDistrictName(districtDto.getDistrictName());
        return district;
    }
}
