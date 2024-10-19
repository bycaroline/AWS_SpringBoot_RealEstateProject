package com.carolineeklund.architecture.services;

import com.carolineeklund.architecture.models.Company;
import com.carolineeklund.architecture.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> getAllCompanies (){
        return companyRepository.findAll();
    }

    public Optional<Company> getOneCompany(Long id){
        return Optional.of(companyRepository.findById(id).orElse(new Company()));
    }

    public Company saveCompany(Company company){
        return companyRepository.save(company);
    }

    public Company patchCompany(Company company, long id){
        Optional<Company> currentCompany = companyRepository.findById(id);
        if (!company.getName().equals(currentCompany.get().getName())){
            currentCompany.get().setName(company.getName());
        }
         return companyRepository.save(currentCompany.get());
    }

    public void removeCompany(Long id){
        companyRepository.deleteById(id);
    }

    public void removeCompany(Company company){
        companyRepository.deleteById(company.getId());
    }

}
