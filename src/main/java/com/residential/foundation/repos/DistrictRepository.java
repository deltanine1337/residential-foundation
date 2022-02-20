package com.residential.foundation.repos;

import com.residential.foundation.model.jpa.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District, Long> {
    District findByDistrictId(Long id);
}
