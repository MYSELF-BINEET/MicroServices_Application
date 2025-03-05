package com.nothing.companies.Company.mapper;

import com.nothing.companies.Company.Company;
import com.nothing.companies.Company.clients.ReviewClient;
import com.nothing.companies.Company.dto.companyDto;
import com.nothing.companies.Company.external.Review;

import java.util.List;

public class CompanyMapper {
    public static companyDto companyConverter(Company company, List<Review> reviews){
        companyDto companyDto=new companyDto();
        companyDto.setId(company.getId());
        companyDto.setDescription(company.getDescription());
        companyDto.setName(company.getName());
        companyDto.setReviews(reviews);

        return companyDto;
    }
}
