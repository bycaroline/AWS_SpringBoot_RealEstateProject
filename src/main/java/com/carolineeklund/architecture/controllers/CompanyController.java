package com.carolineeklund.architecture.controllers;

import com.carolineeklund.architecture.models.Company;
import com.carolineeklund.architecture.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor //så att autowired house repo funkar
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("")
    public ResponseEntity<List<Company>> getAllCompanies(){
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("")
    public ResponseEntity <Optional<Company>> getOneCompany (@PathVariable Long id){
        Optional<Company> company = companyService.getOneCompany(id);
        return ResponseEntity.ok(company);
    }

    @PostMapping("")
    public ResponseEntity<Company> createNewCompany(@RequestBody Company company){
        Company newCompany = companyService.saveCompany(company);
        return ResponseEntity.ok(newCompany);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Company> updateOneCompany(@PathVariable Long id, @RequestBody Company newCompany){
        Company patchedCompany = companyService.patchCompany(newCompany, id);
        return ResponseEntity.ok(patchedCompany);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        companyService.removeCompany(id);
        return ResponseEntity.ok("Company with id " + id + " has been removed");
    }

}
