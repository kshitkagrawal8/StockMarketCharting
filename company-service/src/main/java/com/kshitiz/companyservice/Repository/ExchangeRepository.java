package com.kshitiz.companyservice.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kshitiz.companyservice.Entities.StockExchange;

@Repository
public interface ExchangeRepository extends JpaRepository<StockExchange, Integer> {

	Optional<StockExchange> findByCodeIgnoreCase(String code);

}
