package com.kshitiz.companyservice.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kshitiz.companyservice.Entities.Company;
import com.kshitiz.companyservice.Entities.Sector;
import com.kshitiz.companyservice.Exceptions.CompanyNotFoundException;
import com.kshitiz.companyservice.Repository.CompanyRepository;
import com.kshitiz.companyservice.Repository.SectorRepository;

@Service
public class SectorServiceImpl implements SectorService {

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public String addSector(Sector sector) {
        sectorRepository.save(sector);
        return "Added Sector " + sector.getSectorName() + " to the Database";
    }

    @Override
    public List<Sector> getAllsectors() {
        return sectorRepository.findAll();
    }

    @Override
    public Sector getsector(String name) {
        return sectorRepository.findBySectorName(name);
    }

    @Override
    public List<Company> getAllCompanies(String name) throws CompanyNotFoundException {
        
        List<Company> findBySectorName = companyRepository.findBySectorNameIgnoreCase(name);
        if(findBySectorName == null){throw new CompanyNotFoundException("No companies found in the given sector") ;}
        return findBySectorName;
    }
    
}