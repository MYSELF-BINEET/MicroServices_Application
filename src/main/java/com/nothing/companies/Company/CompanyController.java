package com.nothing.companies.Company;


import com.nothing.companies.Company.dto.companyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {


    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping("")
    public ResponseEntity<List<companyDto>> getAllCompany(){
        return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<String> addCompany(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Company is Created Successfully",HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<companyDto> getCompanyById(@PathVariable Long id){
        return new ResponseEntity<>(companyService.getCompanyById(id),HttpStatus.OK);
    }




    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,@RequestBody Company company){
        companyService.updateCompanyById(company,id);
        return new ResponseEntity<>("Company Updated Successfully",HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        boolean deleted=companyService.deleteCompanyById(id);
        return (deleted) ? new ResponseEntity<>("Company is deleted successfully",HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
