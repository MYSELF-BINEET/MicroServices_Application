package com.nothing.companies.Company.impl;


import com.nothing.companies.Company.Company;
import com.nothing.companies.Company.CompanyRepository;
import com.nothing.companies.Company.CompanyService;
import com.nothing.companies.Company.clients.ReviewClient;
import com.nothing.companies.Company.dto.companyDto;
import com.nothing.companies.Company.external.Review;
import com.nothing.companies.Company.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;


    @Autowired
    private ReviewClient reviewClient;


    @Override
    public List<companyDto> getAllCompanies() {
        List<Company> companies=companyRepository.findAll();
//        List<companyDto> companyDtos=new ArrayList<>();
        return companies.stream().map(this::convertCompany).collect(Collectors.toList());
    }


    private companyDto  convertCompany(Company company){

        List<Review> reviews=reviewClient.getAllReviews(company.getId());

        return CompanyMapper.companyConverter(company,reviews);
    }

    @Override
    public boolean updateCompanyById(Company company, Long id) {
        Optional<Company> companyOptional= companyRepository.findById(id);
        if(companyOptional.isPresent()) {
            Company companyToUpdate=companyOptional.get();
            companyToUpdate.setDescription(company.getDescription());
//            companyToUpdate.setJobs(company.getJobs());
            companyToUpdate.setName(company.getName());
            companyRepository.save(companyToUpdate);
            return true;
        }

        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public companyDto getCompanyById(Long id) {
        Company company=companyRepository.findById(id).orElse(null);
        assert company != null;
        companyDto companyDto=convertCompany(company);

        return companyDto;
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        try{
            companyRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

}
