package com.test.task.services.impl;

import com.test.task.model.dto.TenantDTO;
import com.test.task.mappers.impl.TenantMapperImpl;
import com.test.task.model.jpa.Tenant;
import com.test.task.repos.TenantRepo;
import com.test.task.services.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {

    private final TenantRepo tenantRepo;
    private final TenantMapperImpl tenantMapper;

    @Override
    public List<TenantDTO> getTenants() {
        return tenantRepo.findAll().stream()
                .map(tenantMapper::toTenantDto)
                .collect(Collectors.toList());
    }

    @Override
    public TenantDTO addTenant(TenantDTO tenantDto) {
        tenantRepo.insert(tenantDto.getApartmentNumber(), tenantDto.getFio(), tenantDto.getTelNum(),
                tenantDto.getHouseDto().getHouseId().getHouseNumber(), tenantDto.getHouseDto().getHouseId().getStreet());
        return tenantDto;
    }

    @Override
    @Transactional
    public void deleteTenant(Long id) {
        tenantRepo.deleteById(id);
    }

    @Override
    public TenantDTO updateTenant(Long id, TenantDTO tenantDto) {
        Tenant foundTenant = tenantRepo.findByTenantId(id);
        tenantDto.setTenantId(foundTenant.getTenantId());
        tenantRepo.update(tenantDto.getApartmentNumber(), tenantDto.getFio(), tenantDto.getTelNum(),
                tenantDto.getHouseDto().getHouseId().getHouseNumber(), tenantDto.getHouseDto().getHouseId().getStreet(),
                tenantDto.getTenantId());
        return tenantDto;
    }

    @Override
    public List<TenantDTO> getTenantsByTelNum(String telNum) {
        return tenantRepo.findAllByTelNum(telNum).stream()
                .map(tenantMapper::toTenantDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TenantDTO> getTenantsByFio(String fio) {
        return tenantRepo.findByFio(fio.toLowerCase()).stream()
                .map(tenantMapper::toTenantDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TenantDTO> findTenants(String telNum, String fio) {
        if (telNum != null && fio == null)
            return getTenantsByTelNum(telNum.replace(" ", ""));
        else if (fio != null && telNum == null)
            return getTenantsByFio(fio);
        else return getTenants();
    }
}
