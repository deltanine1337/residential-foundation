package com.test.task.mappers;

import com.test.task.model.dto.DistrictDTO;
import com.test.task.model.jpa.District;

public interface DistrictMapper {
    DistrictDTO toDistrictDto(District district);
    District toDistrict(DistrictDTO districtDto);
}
