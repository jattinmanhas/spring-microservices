package com.springjobapp.companyms.Services;

import com.springjobapp.companyms.Models.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();
    boolean UpdateCompany(Company company, Long id);
    void createCompany(Company company);
    boolean removeCompanyById(Long id);
    Company getCompanyById(Long id);
}
