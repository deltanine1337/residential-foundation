package com.residential.foundation.mappers;

import com.residential.foundation.model.dto.TenantDTO;
import com.residential.foundation.model.jpa.Tenant;

public interface TenantMapper {
    TenantDTO toTenantDto(Tenant tenant);
    Tenant toTenant(TenantDTO tenantDto);
}
