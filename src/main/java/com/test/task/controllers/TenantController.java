package com.test.task.controllers;

import com.test.task.model.Tenant;
import com.test.task.services.TenantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/tenant")
public class TenantController {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService){
        this.tenantService = tenantService;
    }

    @GetMapping
    public Iterable<Tenant> getTenants(@RequestParam(required = false) String telNum,
                                       @RequestParam(required = false) String fio){
        return tenantService.findTenants(telNum, fio);
    }

    @PostMapping
    public Tenant addTenant(@RequestBody Tenant tenant){
        return tenantService.addTenant(tenant);
    }

    @DeleteMapping("/{id}")
    public void deleteTenant(@PathVariable("id") Long id){
        tenantService.deleteTenant(id);
    }

    @PutMapping("/{id}")
    public Tenant updateTenant(@PathVariable("id") Long id, @RequestBody Tenant tenant){
        return tenantService.updateTenant(id, tenant);
    }
}
