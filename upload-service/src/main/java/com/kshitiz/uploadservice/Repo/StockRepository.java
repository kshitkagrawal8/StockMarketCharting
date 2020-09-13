package com.kshitiz.uploadservice.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kshitiz.uploadservice.Entities.StockPrices;

@Repository
public interface StockRepository extends JpaRepository<StockPrices, Integer> {
    
}