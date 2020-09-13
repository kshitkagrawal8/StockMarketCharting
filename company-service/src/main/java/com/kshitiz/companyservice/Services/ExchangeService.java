package com.kshitiz.companyservice.Services;

import java.util.List;

import com.kshitiz.companyservice.Entities.CompanyStockCodes;
import com.kshitiz.companyservice.Entities.Ipo;
import com.kshitiz.companyservice.Entities.StockExchange;
import com.kshitiz.companyservice.Entities.StockPrices;
import com.kshitiz.companyservice.Exceptions.StockNotFoundException;

public interface ExchangeService {

	String addExchange(StockExchange ex);

	List<StockExchange> getExchange();

	StockExchange getExchangeById(String id);

	String addCompanyToExchange(CompanyStockCodes csc);

	List<CompanyStockCodes> getAllCompanies(String id);

	List<StockPrices> getAllStocks(String id);

	List<Ipo> getAllIpos(String id);

	String removeEx(String code) throws StockNotFoundException;

	String removeCompany(CompanyStockCodes company);

}
