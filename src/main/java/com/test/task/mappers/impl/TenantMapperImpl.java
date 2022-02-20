package com.test.task.mappers.impl;

import com.test.task.model.dto.TenantDTO;
import com.test.task.mappers.TenantMapper;
import com.test.task.model.jpa.Tenant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TenantMapperImpl implements TenantMapper {

    private final HouseMapperImpl houseMapper;

    @Override
    public TenantDTO toTenantDto(Tenant tenant) {
        TenantDTO tenantDto = new TenantDTO();
        tenantDto.setTenantId(tenant.getTenantId());
        tenantDto.setApartmentNumber(tenant.getApartmentNumber());
        tenantDto.setFio(tenant.getFio());
        tenantDto.setTelNum(tenant.getTelNum());
        tenantDto.setHouseDto(houseMapper.toHouseDto(tenant.getHouse()));
        return tenantDto;
    }

    @Override
    public Tenant toTenant(TenantDTO tenantDto) {
        Tenant tenant = new Tenant();
        tenant.setTenantId(tenantDto.getTenantId());
        tenant.setApartmentNumber(tenantDto.getApartmentNumber());
        tenant.setFio(tenantDto.getFio());
        tenant.setTelNum(tenantDto.getTelNum());
        tenant.setHouse(houseMapper.toHouse(tenantDto.getHouseDto()));
        return tenant;
    }
}
