package com.test.task.services.interfaces;

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
    Iterable<Tenant> getTenantsByDistrict(District district);
    Iterable<Tenant> getTenantsByHouse(House house);
    Iterable<Tenant> getTenantsByTelNum(String telNum);
    Iterable<Tenant> getTenantsByFio(String fio);
}
