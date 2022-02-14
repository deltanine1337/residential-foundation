package com.test.task.controllers;

import com.test.task.model.House;
import com.test.task.model.keys.HouseId;
import com.test.task.services.HouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/house")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService){
        this.houseService = houseService;
    }

    @GetMapping
    public Iterable<House> getHouses(@RequestParam(required = false) String district,
                                     @RequestParam(required = false) String street){
        return houseService.findHouses(district, street);
    }

    @PostMapping
    public House addHouse(@RequestBody House house){
        return houseService.addHouse(house);
    }

    @DeleteMapping("/{street}/{houseNumber}")
    public void deleteHouse(@PathVariable("street") String street, @PathVariable("houseNumber") int houseNumber){
        houseService.deleteHouse(new HouseId(street, houseNumber));
    }

    @PutMapping("/{street}/{houseNumber}")
    public House updateHouse(@PathVariable("street") String street,
                             @PathVariable("houseNumber") int houseNumber,
                             @RequestBody House house){
        return houseService.updateHouse(new HouseId(street, houseNumber), house);
    }

}
