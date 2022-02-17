package com.test.task.controllers;

import com.test.task.model.House;
import com.test.task.model.keys.HouseId;
import com.test.task.services.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/house")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Iterable<House> getHouses(@RequestParam(required = false) String district,
                                     @RequestParam(required = false) String street) {
        return houseService.findHouses(district, street);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public House addHouse(@RequestBody House house) {
        return houseService.addHouse(house);
    }

    @DeleteMapping("/{street}/{houseNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteHouse(@PathVariable("street") String street, @PathVariable("houseNumber") int houseNumber) {
        houseService.deleteHouse(new HouseId(street, houseNumber));
    }

    @PutMapping("/{street}/{houseNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public House updateHouse(@PathVariable("street") String street,
                             @PathVariable("houseNumber") int houseNumber,
                             @RequestBody House house) {
        return houseService.updateHouse(new HouseId(street, houseNumber), house);
    }

}
