package com.test.task.mappers;

import com.test.task.dto.DistrictDto;
import com.test.task.model.District;

public interface DistrictMapper {
    DistrictDto toDistrictDto(District district);
    District toDistrict(DistrictDto districtDto);
}
