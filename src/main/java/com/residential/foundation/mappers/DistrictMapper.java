package com.residential.foundation.mappers;

import com.residential.foundation.model.dto.DistrictDTO;
import com.residential.foundation.model.jpa.District;

public interface DistrictMapper {
    DistrictDTO toDistrictDto(District district);
    District toDistrict(DistrictDTO districtDto);
}
