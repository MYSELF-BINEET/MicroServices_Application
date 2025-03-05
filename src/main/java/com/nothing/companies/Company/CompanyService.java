package com.nothing.companies.Company;


import com.nothing.companies.Company.dto.companyDto;

import java.util.List;

public interface CompanyService {

    List<companyDto> getAllCompanies();

    boolean updateCompanyById(Company company,Long id);

    void createCompany(Company company);

    companyDto getCompanyById(Long id);

    boolean deleteCompanyById(Long id);

}
