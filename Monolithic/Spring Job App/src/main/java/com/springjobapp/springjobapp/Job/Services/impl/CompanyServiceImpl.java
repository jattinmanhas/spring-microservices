package com.springjobapp.springjobapp.Job.Services.impl;

import com.springjobapp.springjobapp.Job.Models.Company;
import com.springjobapp.springjobapp.Job.Repository.CompanyRepository;
import com.springjobapp.springjobapp.Job.Services.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

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
            companyToUpdate.get().setJobs(company.getJobs());

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
