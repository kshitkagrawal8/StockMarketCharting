package com.kshitiz.companyservice.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kshitiz.companyservice.Entities.Company;
import com.kshitiz.companyservice.Exceptions.CompanyNotFoundException;
import com.kshitiz.companyservice.Services.CompanyService;


@RestController
@RequestMapping("/company")
public class CompanyController {

    CompanyService companyService;
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAllCompany() throws CompanyNotFoundException{
        List<Company> allCompany = companyService.getAllCompany();
        if(allCompany.size() ==0){throw new CompanyNotFoundException("No Company added to DataBase") ;} 
        return ResponseEntity.status(HttpStatus.OK).body(allCompany);
    }
    
    @GetMapping("/like/{name}")
    public ResponseEntity<List<Company>> getCompanyByLikeName(@PathVariable String name) throws CompanyNotFoundException{
        List<Company> companyByLikeName = companyService.getCompanyByLikeName(name);
        if(companyByLikeName.size() ==0){throw new CompanyNotFoundException("No company found with name "+ name) ;}
        return ResponseEntity.status(HttpStatus.OK).body(companyByLikeName);
    }
    @GetMapping("/{name}")
    public ResponseEntity<Company> getCompanyByName(@PathVariable String name) throws CompanyNotFoundException{
        Company companyByLikeName = companyService.getCompanyByName(name);
        if(companyByLikeName == null){throw new CompanyNotFoundException("No company found with name "+ name) ;}
        return ResponseEntity.status(HttpStatus.OK).body(companyByLikeName);
    }
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //add methods
    @PostMapping("/addCompany")
    public ResponseEntity<Map<String, String>> addCompany(@RequestBody Company company){
        logger.info("ADD COMPANY -> {}", company);
        Map<String, String> map = new HashMap<>();
        map.put("status", companyService.addCompany(company));
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
    @PostMapping("/updateCompany/{name}")
    public ResponseEntity<Map<String, String>> updateCompany(@RequestBody Company company, @PathVariable String name){
        Map<String, String> map = new HashMap<>();
        map.put("status", companyService.updateCompany(company, name));
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @GetMapping("/remove/{name}")
    public ResponseEntity<String> removeCompanyByName(@PathVariable String name) throws CompanyNotFoundException{
        String removeCompany = companyService.removeCompany(name);
        if(removeCompany ==null){throw new CompanyNotFoundException("No company found with name "+ name) ;}
        return ResponseEntity.status(HttpStatus.OK).body(removeCompany);
    }

}