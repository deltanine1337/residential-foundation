package com.test.task.services;

import com.test.task.dto.TenantDto;

public interface TenantService {
    Iterable<TenantDto> getTenants();
    TenantDto addTenant(TenantDto tenantDto);
    void deleteTenant(Long id);
    TenantDto updateTenant(Long id, TenantDto tenantDto);
    Iterable<TenantDto> getTenantsByTelNum(String telNum);
    Iterable<TenantDto> getTenantsByFio(String fio);
    Iterable<TenantDto> findTenants(String telNum, String fio);
}
