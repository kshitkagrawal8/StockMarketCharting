package com.kshitiz.companyservice.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kshitiz.companyservice.Entities.Company;
import com.kshitiz.companyservice.Entities.Sector;
import com.kshitiz.companyservice.Exceptions.CompanyNotFoundException;
import com.kshitiz.companyservice.Exceptions.SectorNotFoundException;
import com.kshitiz.companyservice.Services.SectorService;

@RestController
@RequestMapping("/sector")
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @PostMapping("/addSector")
    public ResponseEntity<String> addSector(@RequestBody Sector sector){
        return ResponseEntity.status(HttpStatus.OK).body(sectorService.addSector(sector));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Sector>> getAllSector() throws SectorNotFoundException{
        List<Sector> allsectors = sectorService.getAllsectors();
        if(allsectors.size() == 0){throw new SectorNotFoundException("No Sectors present in DataBase");}
        return ResponseEntity.status(HttpStatus.OK).body(allsectors);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Sector> getSector(@PathVariable String name)throws SectorNotFoundException{
        Sector getsector = sectorService.getsector(name);
        if(getsector == null){throw new SectorNotFoundException("No sector Found with given name,try modifying the name");}
        return ResponseEntity.status(HttpStatus.OK).body(getsector);
    }

    @GetMapping("/{name}/companies")
    public ResponseEntity<List<Company>> getAllCompanies(@PathVariable String name) throws SectorNotFoundException, CompanyNotFoundException{
        List<Company> allCompanies = sectorService.getAllCompanies(name);
        if(allCompanies.size() == 0){throw new SectorNotFoundException("No Sectors found with given name");}
        return ResponseEntity.status(HttpStatus.OK).body(allCompanies);
    }
    
}