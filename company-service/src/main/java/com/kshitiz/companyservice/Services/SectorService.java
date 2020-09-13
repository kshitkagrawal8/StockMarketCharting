package com.kshitiz.companyservice.Services;

import java.util.List;

import com.kshitiz.companyservice.Entities.Company;
import com.kshitiz.companyservice.Entities.Sector;
import com.kshitiz.companyservice.Exceptions.CompanyNotFoundException;

public interface SectorService {

	String addSector(Sector sector);

	List<Sector> getAllsectors();

	Sector getsector(String id);

	List<Company> getAllCompanies(String name) throws CompanyNotFoundException;

}
