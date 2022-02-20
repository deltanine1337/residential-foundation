package com.test.task.controllers;

import com.test.task.model.dto.TenantDto;
import com.test.task.services.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tenant")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Iterable<TenantDto> getTenants(@RequestParam(required = false) String telNum,
                                          @RequestParam(required = false) String fio){
        return tenantService.findTenants(telNum, fio);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public TenantDto addTenant(@RequestBody TenantDto tenantDto){
        return tenantService.addTenant(tenantDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTenant(@PathVariable("id") Long id){
        tenantService.deleteTenant(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public TenantDto updateTenant(@PathVariable("id") Long id, @RequestBody TenantDto tenantDto){
        return tenantService.updateTenant(id, tenantDto);
    }
}
