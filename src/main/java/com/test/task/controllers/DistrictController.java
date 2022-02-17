package com.test.task.controllers;

import com.test.task.model.District;
import com.test.task.services.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/district")
@RequiredArgsConstructor
public class DistrictController {

    private final DistrictService districtService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Iterable<District> getDistricts() {
        return districtService.getDistricts();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public District addDistrict(@RequestBody District district) {
        return districtService.addDistrict(district);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDistrict(@PathVariable("id") Long id) {
        districtService.deleteDistrict(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public District updateDistrict(@PathVariable("id") Long id, @RequestBody District district) {
        return districtService.updateDistrict(id, district);
    }

}
