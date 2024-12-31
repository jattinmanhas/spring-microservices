package com.springjobapp.springjobapp.Job.Services;

import com.springjobapp.springjobapp.Job.Models.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean UpdateCompany(Company company, Long id);
    void createCompany(Company company);
    boolean removeCompanyById(Long id);
    Company getCompanyById(Long id);
}
