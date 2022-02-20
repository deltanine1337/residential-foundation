package com.test.task.services;

import com.test.task.model.dto.TenantDTO;

public interface TenantService {
    Iterable<TenantDTO> getTenants();
    TenantDTO addTenant(TenantDTO tenantDto);
    void deleteTenant(Long id);
    TenantDTO updateTenant(Long id, TenantDTO tenantDto);
    Iterable<TenantDTO> getTenantsByTelNum(String telNum);
    Iterable<TenantDTO> getTenantsByFio(String fio);
    Iterable<TenantDTO> findTenants(String telNum, String fio);
}
