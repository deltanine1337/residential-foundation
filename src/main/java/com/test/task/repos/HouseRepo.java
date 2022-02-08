package com.test.task.repos;

import com.test.task.model.District;
import com.test.task.model.House;
import com.test.task.model.keys.HouseId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface HouseRepo extends CrudRepository<House, Long> {
    Iterable<House> findAllByDistrict(District district);
    @Query(value = "select * from house where street = :street", nativeQuery = true)
    Iterable<House> findAllByStreet(@Param("street") String street);
    void deleteByHouseId(HouseId houseId);
    House findByHouseId(HouseId houseId);
}
