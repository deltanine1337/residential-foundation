package com.test.task.repos;

import com.test.task.model.jpa.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepo extends JpaRepository<District, Long> {
    District findByDistrictId(Long id);
}
