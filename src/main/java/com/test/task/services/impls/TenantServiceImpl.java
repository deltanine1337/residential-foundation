package com.test.task.services.impls;

import com.test.task.model.District;
import com.test.task.model.House;
import com.test.task.model.Tenant;
import com.test.task.repos.TenantRepo;
import com.test.task.services.interfaces.TenantService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TenantServiceImpl implements TenantService {

    private final TenantRepo tenantRepo;

    public TenantServiceImpl(TenantRepo tenantRepo){
        this.tenantRepo = tenantRepo;
    }

    public Iterable<Tenant> getTenants() {
        return tenantRepo.findAll();
    }

    public ResponseEntity<Tenant> addTenant(Tenant tenant) {
        tenantRepo.save(tenant);
        return ResponseEntity.ok().body(tenant);
    }

    public void deleteTenant(Long id) {
        tenantRepo.deleteById(id);
    }

    public ResponseEntity<Tenant> updateTenant(Long id, Tenant tenant) {
        Tenant foundTenant = tenantRepo.findByTenantId(id);
        tenant.setTenantId(foundTenant.getTenantId());
        tenantRepo.save(tenant);
        return ResponseEntity.ok().body(tenant);
    }

    public Iterable<Tenant> getTenantsByDistrict(District district) {
        return tenantRepo.findAllByDistrict(district.getDistrictName());
    }

    public Iterable<Tenant> getTenantsByHouse(House house) {
        return tenantRepo.findAllByHouse(house);
    }

    public Iterable<Tenant> getTenantsByStreet(String street) {
        return tenantRepo.findAllByStreet(street);
    }

    public Iterable<Tenant> getTenantsByTelNum(String telNum) {
        return tenantRepo.findAllByTelNum(telNum);
    }

    public Iterable<Tenant> getTenantsByFio(String fio) {
        return tenantRepo.findAllByFio(fio);
    }
}
