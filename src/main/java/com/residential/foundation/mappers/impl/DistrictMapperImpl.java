package com.residential.foundation.mappers.impl;

import com.residential.foundation.model.dto.DistrictDTO;
import com.residential.foundation.model.jpa.District;
import com.residential.foundation.mappers.DistrictMapper;
import org.springframework.stereotype.Service;

@Service
public class DistrictMapperImpl implements DistrictMapper {

    @Override
    public DistrictDTO toDistrictDto(District district) {
        DistrictDTO districtDto = new DistrictDTO();
        districtDto.setDistrictId(district.getDistrictId());
        districtDto.setDistrictName(district.getDistrictName());
        return districtDto;
    }

    @Override
    public District toDistrict(DistrictDTO districtDto) {
        District district = new District();
        district.setDistrictId(districtDto.getDistrictId());
        district.setDistrictName(districtDto.getDistrictName());
        return district;
    }
}
