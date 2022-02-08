package com.test.task.repos;

import com.test.task.model.House;
import org.springframework.data.repository.CrudRepository;

public interface HouseRepo extends CrudRepository<House, Long> {
}
