package com.test.task.mappers;

import com.test.task.model.dto.DistrictDto;
import com.test.task.model.jpa.District;

public interface DistrictMapper {
    DistrictDto toDistrictDto(District district);
    District toDistrict(DistrictDto districtDto);
}
