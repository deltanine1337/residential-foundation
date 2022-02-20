package com.test.task.repos;

import com.test.task.model.jpa.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
    @Modifying
    @Transactional
    @Query(value = "insert into tenant(apartment_number,fio,tel_num,house_number, street) " +
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

    @Query(value = "select * from tenant where lower(fio) like %:fio%", nativeQuery = true)
    List<Tenant> findByFio(@Param("fio") String fio);

    @Query(value = "select * from tenant where tel_num like %:telNum%", nativeQuery = true)
    List<Tenant> findAllByTelNum(@Param("telNum") String telNum);

    Tenant findByTenantId(Long id);
}
