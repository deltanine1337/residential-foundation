package com.test.task.controllers;

import com.test.task.model.District;
import com.test.task.services.DistrictService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/district")
public class DistrictController {

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService){
        this.districtService = districtService;
    }

    @GetMapping("/get")
    public Iterable<District> getDistricts(){
        return districtService.getDistricts();
    }

    @PostMapping("/add")
    public ResponseEntity<District> addDistrict(@RequestBody District district){
        return districtService.addDistrict(district);
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public void deleteDistrict(@PathVariable("id") Long id){
        districtService.deleteDistrict(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<District> updateDistrict(@PathVariable("id") Long id, @RequestBody District district){
        return districtService.updateDistrict(id, district);
    }

}
