package com.kshitiz.companyservice.Services;

import java.util.List;

import com.kshitiz.companyservice.Entities.StockPrices;
import com.kshitiz.companyservice.Exceptions.CompanyNotFoundException;
import com.kshitiz.companyservice.Exceptions.RegistrationError;
import com.kshitiz.companyservice.Exceptions.StockNotFoundException;
import com.kshitiz.companyservice.models.Dates;

public interface StocksService {

    List<StockPrices> getAllStockPricesByName(String name) throws CompanyNotFoundException, RegistrationError;

	List<StockPrices> getStockPriceByCompanyStockEx(String name, String stockCode) throws CompanyNotFoundException, RegistrationError;

	List<StockPrices> getStockPricesByRange(Dates dates, String name) throws CompanyNotFoundException, RegistrationError;
	
	String addStock(StockPrices sp, String name);

	List<StockPrices> getAllStocks();

	StockPrices getStockPrices(Integer id);

	String removeStock(Integer id) throws StockNotFoundException;

	String removeAll();
    

}
