package com.test.task.mappers;

import com.test.task.model.dto.TenantDTO;
import com.test.task.model.jpa.Tenant;

public interface TenantMapper {
    TenantDTO toTenantDto(Tenant tenant);
    Tenant toTenant(TenantDTO tenantDto);
}
