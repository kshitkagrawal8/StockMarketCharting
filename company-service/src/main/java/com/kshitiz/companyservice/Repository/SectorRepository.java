package com.kshitiz.companyservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kshitiz.companyservice.Entities.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Integer> {

    Sector findBySectorName(String name);

}
