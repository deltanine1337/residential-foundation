package com.test.task.controllers;

import com.test.task.model.dto.TenantDTO;
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
    public Iterable<TenantDTO> getTenants(@RequestParam(required = false) String telNum,
                                          @RequestParam(required = false) String fio){
        return tenantService.findTenants(telNum, fio);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public TenantDTO addTenant(@RequestBody TenantDTO tenantDto){
        return tenantService.addTenant(tenantDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTenant(@PathVariable("id") Long id){
        tenantService.deleteTenant(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public TenantDTO updateTenant(@PathVariable("id") Long id, @RequestBody TenantDTO tenantDto){
        return tenantService.updateTenant(id, tenantDto);
    }
}
