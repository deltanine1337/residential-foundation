package com.test.task.controllers;

import com.test.task.model.dto.HouseDTO;
import com.test.task.model.jpa.keys.HouseId;
import com.test.task.services.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/house")
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<HouseDTO> getHouses(@RequestParam(required = false) String district,
                                    @RequestParam(required = false) String street) {
        return houseService.findHouses(district, street);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public HouseDTO addHouse(@RequestBody HouseDTO houseDto) {
        return houseService.addHouse(houseDto);
    }

    @DeleteMapping("/{street}/{houseNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteHouse(@PathVariable("street") String street, @PathVariable("houseNumber") int houseNumber) {
        houseService.deleteHouse(new HouseId(street, houseNumber));
    }

    @PutMapping("/{street}/{houseNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public HouseDTO updateHouse(@PathVariable("street") String street,
                                @PathVariable("houseNumber") int houseNumber,
                                @RequestBody HouseDTO houseDto) {
        return houseService.updateHouse(new HouseId(street, houseNumber), houseDto);
    }

}
