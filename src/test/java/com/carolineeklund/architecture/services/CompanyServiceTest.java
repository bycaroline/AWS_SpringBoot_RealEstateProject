package com.carolineeklund.architecture.services;

import com.carolineeklund.architecture.models.Company;
import com.carolineeklund.architecture.models.House;
import com.carolineeklund.architecture.repositories.CompanyRepository;
import com.carolineeklund.architecture.repositories.HouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CompanyServiceTest {
    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    private House house;
    private Company company;

    @BeforeEach
    public void setup() {
        company = new Company();
        company.setId(1L);
        company.setName("Example Company");

    }

    @Test
    void getAllCompanies() {
        // Arrange
        when(companyRepository.findAll()).thenReturn((List.of(company)));

        // Act
        List<Company> result = companyService.getAllCompanies();

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    void getOneCompany() {
        // Arrange
        when(companyRepository.findById(1L)).thenReturn(java.util.Optional.of(company));

        // Act
        Company result = companyService.getOneCompany(1L).get();

        // Assert
        assertEquals(company.getName(), result.getName());
    }

    @Test
    void saveCompany() {
        // Arrange
        when(companyRepository.save(company)).thenReturn(company);

        // Act
        Company result = companyService.saveCompany(company);

        // Assert
        assertEquals(company.getName(), result.getName());
    }

    @Test
    void patchCompany() {
        // Arrange
        when(companyRepository.findById(1L)).thenReturn(java.util.Optional.of(company));
        Company newCompany = new Company();
        newCompany.setId(1L);
        newCompany.setName("New Company");

        // Act
        Company result = companyService.patchCompany(newCompany, 1L);

        // Assert
        assertEquals(newCompany.getName(), result.getName());
    }

    @Test
    void removeCompany() {
        // Arrange
        when(companyRepository.findById(1L)).thenReturn(java.util.Optional.of(company));

        // Act
        companyService.removeCompany(1L);

        // Assert
        assertEquals(0, companyService.getAllCompanies().size());
    }

    @Test
    void testRemoveCompany() {
        // Arrange
        when(companyRepository.findById(1L)).thenReturn(java.util.Optional.of(company));

        // Act
        companyService.removeCompany(company);

        // Assert
        assertEquals(0, companyService.getAllCompanies().size());
        
    }
}