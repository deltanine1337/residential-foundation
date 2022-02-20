package com.test.task.services.impl;

import com.test.task.model.dto.TenantDTO;
import com.test.task.mappers.impl.TenantMapperImpl;
import com.test.task.model.jpa.Tenant;
import com.test.task.repos.TenantRepository;
import com.test.task.services.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TenantServiceImpl implements TenantService {

    private final TenantRepository tenantRepository;
    private final TenantMapperImpl tenantMapper;

    @Override
    public List<TenantDTO> getTenants() {
        return tenantRepository.findAll().stream()
                .map(tenantMapper::toTenantDto)
                .collect(Collectors.toList());
    }

    @Override
    public TenantDTO addTenant(TenantDTO tenantDto) {
        tenantRepository.insert(tenantDto.getApartmentNumber(), tenantDto.getFio(), tenantDto.getTelNum(),
                tenantDto.getHouseDto().getHouseId().getHouseNumber(), tenantDto.getHouseDto().getHouseId().getStreet());
        return tenantDto;
    }

    @Override
    @Transactional
    public void deleteTenant(Long id) {
        tenantRepository.deleteById(id);
    }

    @Override
    public TenantDTO updateTenant(Long id, TenantDTO tenantDto) {
        Tenant foundTenant = tenantRepository.findByTenantId(id);
        tenantRepository.update(tenantDto.getApartmentNumber(), tenantDto.getFio(), tenantDto.getTelNum(),
                tenantDto.getHouseDto().getHouseId().getHouseNumber(), tenantDto.getHouseDto().getHouseId().getStreet(),
                foundTenant.getTenantId());
        return tenantDto;
    }

    @Override
    public List<TenantDTO> getTenantsByTelNum(String telNum) {
        return tenantRepository.findAllByTelNum(telNum).stream()
                .map(tenantMapper::toTenantDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TenantDTO> getTenantsByFio(String fio) {
        return tenantRepository.findByFio(fio.toLowerCase()).stream()
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
