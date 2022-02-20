package com.test.task.mappers;

import com.test.task.model.dto.TenantDto;
import com.test.task.model.jpa.Tenant;

public interface TenantMapper {
    TenantDto toTenantDto(Tenant tenant);
    Tenant toTenant(TenantDto tenantDto);
}
