package com.test.task.repos;

import com.test.task.model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface TenantRepo extends JpaRepository<Tenant, Long> {

    @Query(value = "select * from tenant where street = :street and house_number = :houseNumber", nativeQuery = true)
    Iterable<Tenant> findByHouse(@Param("street") String street, @Param("houseNumber") int houseNumber);
    @Modifying
    @Transactional
    @Query(value = "insert into tenant(apartment_number,fio,tel_num,house_number,street) " +
            "values(:an,:fio,:telnum,:hn,:street)", nativeQuery = true)
    void insert(@Param("an") int an, @Param("fio") String fio, @Param("telnum") String telnum, @Param("hn") int hn,
               @Param("street") String street);
    @Modifying
    @Transactional
    @Query(value = "update tenant set apartment_number=:an,fio=:fio,tel_num=:telnum,house_number=:hn,street=:street where" +
            " tenant_id=:id",
            nativeQuery = true)
    void update(@Param("an") int an, @Param("fio") String fio, @Param("telnum") String telnum, @Param("hn") int hn,
               @Param("street") String street, @Param("id") Long id);
    Iterable<Tenant> findAllByFio(String fio);
    @Query(value = "select * from tenant where (house_number, street) in (select house_number, street from house where " +
            "district_id in (select district_id from district where district_name = :district))", nativeQuery = true)
    Iterable<Tenant> findAllByDistrict(@Param("district") String district);
    @Query(value = "select * from tenant where street = :street", nativeQuery = true)
    Iterable<Tenant> findAllByStreet(@Param("street") String street);
    Iterable<Tenant> findAllByTelNum(String telnum);
    Tenant findByTenantId(Long id);
}
