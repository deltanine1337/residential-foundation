package com.test.task.services.impl;

import com.test.task.model.Tenant;
import com.test.task.repos.TenantRepo;
import com.test.task.services.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {

    private final TenantRepo tenantRepo;

    @Override
    public Iterable<Tenant> getTenants() {
        return tenantRepo.findAll();
    }

    @Override
    public Tenant addTenant(Tenant tenant) {
        tenantRepo.insert(tenant.getApartmentNumber(), tenant.getFio(), tenant.getTelNum(),
                tenant.getHouse().getHouseId().getHouseNumber(), tenant.getHouse().getHouseId().getStreet());
        return tenant;
    }

    @Override
    @Transactional
    public void deleteTenant(Long id) {
        tenantRepo.deleteById(id);
    }

    @Override
    public Tenant updateTenant(Long id, Tenant tenant) {
        Tenant foundTenant = tenantRepo.findByTenantId(id);
        tenant.setTenantId(foundTenant.getTenantId());
        tenantRepo.update(tenant.getApartmentNumber(), tenant.getFio(), tenant.getTelNum(),
                tenant.getHouse().getHouseId().getHouseNumber(), tenant.getHouse().getHouseId().getStreet(), tenant.getTenantId());
        return tenant;
    }

    @Override
    public Iterable<Tenant> getTenantsByDistrict(String districtName) {
        return tenantRepo.findAllByDistrict(districtName);
    }

    @Override
    public Iterable<Tenant> getTenantsByHouse(String street, int houseNumber) {
        return tenantRepo.findByHouse(street, houseNumber);
    }

    @Override
    public Iterable<Tenant> getTenantsByStreet(String street) {
        return tenantRepo.findAllByStreet(street);
    }

    @Override
    public Iterable<Tenant> getTenantsByTelNum(String telNum) {
        return tenantRepo.findAllByTelNum(telNum);
    }

    @Override
    public Iterable<Tenant> getTenantsByFio(String fio) {
        return tenantRepo.findByFio(fio.toLowerCase());
    }

    @Override
    public Iterable<Tenant> findTenants(String telNum, String fio) {
        if (telNum != null)
            return getTenantsByTelNum("+" + telNum.replace(" ", ""));
        else if (fio != null)
            return getTenantsByFio(fio);
        else return getTenants();
    }
}
