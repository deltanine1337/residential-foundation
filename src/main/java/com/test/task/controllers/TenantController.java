package com.test.task.controllers;

import com.test.task.model.Tenant;
import com.test.task.services.interfaces.TenantService;
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

    @GetMapping("/get")
    public Iterable<Tenant> getTenants(@RequestParam(required = false) String street,
                                       @RequestParam(required = false) Integer houseNumber,
                                       @RequestParam(required = false) String telNum,
                                       @RequestParam(required = false) String districtName,
                                       @RequestParam(required = false) String fio){
        if (street != null && houseNumber == null)
            return tenantService.getTenantsByStreet(street);
        else if (houseNumber != null && street != null)
            return tenantService.getTenantsByHouse(street, houseNumber);
        else if (telNum != null)
            return tenantService.getTenantsByTelNum("+" + telNum.replace(" ", ""));
        else if (fio != null)
            return tenantService.getTenantsByFio(fio);
        else if (districtName != null)
            return tenantService.getTenantsByDistrict(districtName);
        else return tenantService.getTenants();
    }

    @PostMapping("/add")
    public @ResponseBody ResponseEntity<Tenant> addTenant(@RequestBody Tenant tenant){
        return tenantService.addTenant(tenant);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTenant(@PathVariable("id") Long id){
        tenantService.deleteTenant(id);
    }

    @PutMapping("/update/{id}")
    public @ResponseBody ResponseEntity<Tenant> updateTenant(@PathVariable("id") Long id, @RequestBody Tenant tenant){
        return tenantService.updateTenant(id, tenant);
    }
}