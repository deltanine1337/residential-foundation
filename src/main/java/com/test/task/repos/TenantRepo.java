package com.test.task.repos;

import com.test.task.model.House;
import com.test.task.model.Tenant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TenantRepo extends CrudRepository<Tenant, Long> {

    Iterable<Tenant> findAllByHouse(House house);
    Iterable<Tenant> findAllByFio(String fio);
    @Query(value = "select * from tenant where district_id in (select district_id from district where " +
            "district_name = :district)", nativeQuery = true)
    Iterable<Tenant> findAllByDistrict(@Param("district") String district);
    @Query(value = "select * from tenant where street = :street", nativeQuery = true)
    Iterable<Tenant> findAllByStreet(@Param("street") String street);
    Iterable<Tenant> findAllByTelNum(String telnum);
    Tenant findByTenantId(Long id);
}
