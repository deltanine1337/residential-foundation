package com.test.task.controllers;

import com.test.task.model.House;
import com.test.task.model.keys.HouseId;
import com.test.task.services.interfaces.HouseService;
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

    @GetMapping("/get")
    public Iterable<House> getHouses(@RequestParam(required = false) String district,
                                     @RequestParam(required = false) String street){
        if (district == null && street == null)
            return houseService.getHouses();
        else if (district != null && street == null)
            return houseService.getHousesByDistrict(district);
        else if (district == null && street != null)
            return houseService.getHousesByStreet(street);
        else return null;
    }

    @PostMapping("/add")
    public @ResponseBody ResponseEntity<House> addHouse(@RequestBody House house){
        return houseService.addHouse(house);
    }

    @Transactional
    @DeleteMapping("/delete/{street}/{houseNumber}")
    public void deleteHouse(@PathVariable("street") String street, @PathVariable("houseNumber") int houseNumber){
        houseService.deleteHouse(new HouseId(street, houseNumber));
    }

    @PutMapping("update/{street}/{houseNumber}")
    public @ResponseBody ResponseEntity<House> updateHouse(@PathVariable("street") String street,
                                                           @PathVariable("houseNumber") int houseNumber,
                                                           @RequestBody House house){
        return houseService.updateHouse(new HouseId(street, houseNumber), house);
    }

}
