package com.kshitiz.companyservice.Services;

import java.util.List;

import com.kshitiz.companyservice.Entities.Company;
import com.kshitiz.companyservice.Exceptions.CompanyNotFoundException;

public interface CompanyService {

	List<Company> getAllCompany();

	List<Company> getCompanyByLikeName(String name);

	String addCompany(Company company);

	String updateCompany(Company company, String name);

	String removeCompany(String name) throws CompanyNotFoundException;

	Company getCompanyByName(String name);
    
}