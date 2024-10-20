package com.carolineeklund.architecture.controllers;

import com.carolineeklund.architecture.models.Company;
import com.carolineeklund.architecture.models.House;
import com.carolineeklund.architecture.services.HouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/house")
@RequiredArgsConstructor //så att autowired house repo funkar
public class HouseController {

    private final HouseService houseService;

    @GetMapping("")
    public ResponseEntity<List<House>> getAllHouses(){
        List<House> houses = houseService.getAllHouses();
        return ResponseEntity.ok(houses);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Optional<House>> getOneHouse (@PathVariable Long id){
        Optional<House> house = houseService.getOneHouse(id);
        return ResponseEntity.ok(house);
    }

    @PostMapping("")
    public ResponseEntity<House> createNewHouse(@RequestBody House house){
        House newHouse = houseService.saveHouse(house);
        return ResponseEntity.ok(newHouse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<House> updateOneHouse(@PathVariable Long id, @RequestBody House newHouse){
        House patchedHouse = houseService.patchHouse(newHouse, id);
        return ResponseEntity.ok(patchedHouse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHouse(@PathVariable Long id){
        houseService.removeHouse(id);
        return ResponseEntity.ok("House with id " + id + " has been removed");
    }

}

