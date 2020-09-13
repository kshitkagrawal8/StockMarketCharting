package com.kshitiz.companyservice.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kshitiz.companyservice.Entities.Company;
import com.kshitiz.companyservice.Exceptions.CompanyNotFoundException;
import com.kshitiz.companyservice.Repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> getAllCompany()  {
        return companyRepository.findAll();
    }

    @Override
    public List<Company> getCompanyByLikeName(String name) {
        return companyRepository.findByCompanyNameIgnoreCaseContaining(name);
    }

    //Add services
    @Override
    public String addCompany(Company company) {
        
        if(companyRepository.findByCompanyNameIgnoreCase(company.getCompanyName()) == null){
            companyRepository.save(company);
            return "successful";
        }
        else{
            return "failed";
        }
    }

    @Override
    @Transactional
    public String updateCompany(Company company, String name) {
        Company data = companyRepository.findByCompanyNameIgnoreCase(name);
        if(data ==null){
            companyRepository.save(company);
            return "new Upload";
        }else{
            data.setCompanyDetails(company.getCompanyDetails());
            data.setSectorName(company.getSectorName());
            data.setCompanyName(company.getCompanyName());
            data.setTurnover(company.getTurnover());      
            //companyRepository.saveAndFlush(data);
            return "successful";
        }
        
    }

    @Override
    @Transactional
    public String removeCompany(String name) throws CompanyNotFoundException{
        Company company = companyRepository.findByCompanyNameIgnoreCase(name);
        if(company == null){throw new CompanyNotFoundException("No company found with name "+ name) ;}
        companyRepository.delete(company);
        return "Removed " + company.getCompanyName() + " from Database.";
    }

    @Override
    public Company getCompanyByName(String name) {
        return companyRepository.findByCompanyNameIgnoreCase(name);
    }
    
}