package com.springjobapp.companyms.Services.Impl;


import com.springjobapp.companyms.Models.Company;
import com.springjobapp.companyms.Repository.CompanyRepository;
import com.springjobapp.companyms.Services.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean UpdateCompany(Company company, Long id) {
        Optional<Company> companyToUpdate = companyRepository.findById(id);
        if(companyToUpdate.isPresent()){
            companyToUpdate.get().setName(company.getName());
            companyToUpdate.get().setDescription(company.getDescription());
            // companyToUpdate.get().setJobs(company.getJobs());

            companyRepository.save(companyToUpdate.get());
            return true;
        }

        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean removeCompanyById(Long id) {
        try{
            companyRepository.deleteById(id);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}
