package com.test.task.mappers;

import com.test.task.dto.TenantDto;
import com.test.task.model.Tenant;

public interface TenantMapper {
    TenantDto toTenantDto(Tenant tenant);
    Tenant toTenant(TenantDto tenantDto);
}
