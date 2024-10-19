package com.carolineeklund.architecture.services;

import com.carolineeklund.architecture.models.House;
import com.carolineeklund.architecture.repositories.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;

    public List<House> getAllHouses (){
        return houseRepository.findAll();
    }

    public Optional<House> getOneHouse(Long id){
        return Optional.of(houseRepository.findById(id).orElse(new House()));
    }

    public House saveHouse(House house){
        return houseRepository.save(house);
    }

    public House patchHouse(House house, long id){
        Optional<House> currentHouse = houseRepository.findById(id);
        if (!house.getName().equals(currentHouse.get().getName())){
            currentHouse.get().setName(house.getName());
        }
        return houseRepository.save(currentHouse.get());
    }

    public void removeHouse(Long id){
        houseRepository.deleteById(id);
    }

    public void removeHouse(House house){
        houseRepository.deleteById(house.getId());
    }

}
