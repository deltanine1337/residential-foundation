package com.residential.foundation.services;

import com.residential.foundation.model.dto.TenantDTO;

import java.util.List;

public interface TenantService {
    List<TenantDTO> getTenants();
    TenantDTO addTenant(TenantDTO tenantDto);
    void deleteTenant(Long id);
    TenantDTO updateTenant(Long id, TenantDTO tenantDto);
    List<TenantDTO> getTenantsByTelNum(String telNum);
    List<TenantDTO> getTenantsByFio(String fio);
    List<TenantDTO> findTenants(String telNum, String fio);
}
