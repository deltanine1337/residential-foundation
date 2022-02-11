package com.test.task.services.impls;

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
        tenantRepo.save1(tenant.getApartmentNumber(), tenant.getFio(), tenant.getTelNum(),
                tenant.getHouse().getHouseId().getHouseNumber(), tenant.getHouse().getHouseId().getStreet());
        return ResponseEntity.ok().body(tenant);
    }

    public void deleteTenant(Long id) {
        tenantRepo.deleteById(id);
    }

    public ResponseEntity<Tenant> updateTenant(Long id, Tenant tenant) {
        Tenant foundTenant = tenantRepo.findByTenantId(id);
        tenant.setTenantId(foundTenant.getTenantId());
        tenantRepo.update(tenant.getApartmentNumber(), tenant.getFio(), tenant.getTelNum(),
                tenant.getHouse().getHouseId().getHouseNumber(), tenant.getHouse().getHouseId().getStreet(), tenant.getTenantId());
        return ResponseEntity.ok().body(tenant);
    }

    public Iterable<Tenant> getTenantsByDistrict(String districtName) {
        return tenantRepo.findAllByDistrict(districtName);
    }

    public Iterable<Tenant> getTenantsByHouse(String street, int houseNumber) {
        return tenantRepo.findByHouse(street, houseNumber);
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
