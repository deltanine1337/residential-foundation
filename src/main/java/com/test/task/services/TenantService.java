package com.test.task.services;

import com.test.task.model.District;
import com.test.task.model.House;
import com.test.task.model.Tenant;
import org.springframework.http.ResponseEntity;

public interface TenantService {
    Iterable<Tenant> getTenants();
    ResponseEntity<Tenant> addTenant(Tenant tenant);
    void deleteTenant(Long id);
    ResponseEntity<Tenant> updateTenant(Long id, Tenant tenant);
    Iterable<Tenant> getTenantsByStreet(String street);
    Iterable<Tenant> getTenantsByDistrict(String districtName);
    Iterable<Tenant> getTenantsByHouse(String street, int houseNumber);
    Iterable<Tenant> getTenantsByTelNum(String telNum);
    Iterable<Tenant> getTenantsByFio(String fio);
    Iterable<Tenant> findTenants(String telNum, String fio);
}
