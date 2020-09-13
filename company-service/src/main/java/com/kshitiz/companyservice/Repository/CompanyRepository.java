package com.kshitiz.companyservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kshitiz.companyservice.Entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer>{

    List<Company> findByCompanyNameIgnoreCaseContaining(String name);

    Company findByCompanyNameIgnoreCase(String name);
    
    List<Company> findByCompanyNameIgnoreCaseIn(List<String> names);

	List<Company> findBySectorNameIgnoreCase(String id); 
    
}