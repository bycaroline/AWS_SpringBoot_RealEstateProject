package com.carolineeklund.architecture.services;

import com.carolineeklund.architecture.models.Company;
import com.carolineeklund.architecture.models.House;
import com.carolineeklund.architecture.repositories.CompanyRepository;
import com.carolineeklund.architecture.repositories.HouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class HouseServiceTest {

    @Mock
    private HouseRepository houseRepository;

    @InjectMocks
    private HouseService houseService;

    private House house;
    private Company company;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);  // Initierar mock-objekten

        company = new Company();
        company.setId(1L);
        company.setName("Example Company");

        this.house = new House();
        house.setId(1L);
        house.setName("Freja");
        house.setType("Attefall");
        house.setSize(25);
        house.setCost(2000000);
        house.setReadyMade(false);
        house.setCompany(company);
    }

    @Test
    public void testGetAllHouses() {
        // Arrange
        when(houseRepository.findAll()).thenReturn(Arrays.asList(
                new House(1L, "House 1", "Type 1", 100, 1000000, true, company),
                new House(2L, "House 2", "Type 2", 200, 2000000, false, company)
        ));

        // Act
        List<House> result = houseService.getAllHouses();

        // Assert
        assertEquals(2, result.size());
        assertEquals("House 1", result.get(0).getName());
        assertEquals("House 2", result.get(1).getName());
    }

    @Test
    void getOneHouse() {
        // Arrange
        when(houseRepository.findById(1L)).thenReturn(java.util.Optional.of(house));

        // Act
        House result = houseService.getOneHouse(1L).get();

        // Assert
        assertEquals(house.getName(), result.getName());
    }

    @Test
    void saveHouse() {
        // Arrange
        when(houseRepository.save(house)).thenReturn(house);

        // Act
        House result = houseService.saveHouse(house);

        // Assert
        assertEquals(house.getName(), result.getName());
    }

    @Test
    void patchHouse() {
        // Arrange
        House existingHouse = new House();
        existingHouse.setId(1L);
        existingHouse.setName("Old Company");

        House updatedHouse = new House();
        updatedHouse.setId(1L);
        updatedHouse.setName("New House");

        when(houseRepository.findById(1L)).thenReturn(Optional.of(existingHouse));
        when(houseRepository.save(any(House.class))).thenReturn(existingHouse);

        // Act
        House result = houseService.patchHouse(updatedHouse, 1L);

        // Assert
        assertNotNull(result);
        assertEquals("New House", result.getName()); // Se till att namnet uppdaterades
        verify(houseRepository).findById(1L); // Verifiera att findById kallades
        verify(houseRepository).save(existingHouse); // Verifiera att save kallades
    }

    @Test
    void removeHouse() {
        // Arrange
        when(houseRepository.findById(1L)).thenReturn(java.util.Optional.of(house));

        // Act
        houseService.removeHouse(1L);

        // Assert
        assertEquals(0, houseService.getAllHouses().size());
    }

    @Test
    void testRemoveHouse() {
        // Arrange
        when(houseRepository.findById(1L)).thenReturn(java.util.Optional.of(house));

        // Act
        houseService.removeHouse(house);

        // Assert
        assertEquals(0, houseService.getAllHouses().size());
    }
}