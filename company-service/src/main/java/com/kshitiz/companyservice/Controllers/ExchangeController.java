package com.kshitiz.companyservice.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kshitiz.companyservice.Entities.CompanyStockCodes;
import com.kshitiz.companyservice.Entities.Ipo;
import com.kshitiz.companyservice.Entities.StockExchange;
import com.kshitiz.companyservice.Entities.StockPrices;
import com.kshitiz.companyservice.Exceptions.CompanyNotFoundException;
import com.kshitiz.companyservice.Exceptions.IpoNotFoundException;
import com.kshitiz.companyservice.Exceptions.StockExchangeNotFoundException;
import com.kshitiz.companyservice.Exceptions.StockNotFoundException;
import com.kshitiz.companyservice.Services.ExchangeService;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @PostMapping("/addUpdateExchange")
    public ResponseEntity<Map<String, String>> addExchange(@RequestBody StockExchange ex){
        String addEx = exchangeService.addExchange(ex);
        Map<String, String> map = new HashMap<>();
        map.put("status", addEx);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StockExchange>> getAllExchanges() throws StockExchangeNotFoundException{
        List<StockExchange> exchange = exchangeService.getExchange();
        if(exchange.size() ==0){ throw new StockExchangeNotFoundException("No Stock Exchange Listed in the DataBase");}
        return ResponseEntity.status(HttpStatus.OK).body(exchange);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockExchange> getExchangeById(@PathVariable String id) throws StockExchangeNotFoundException{
        
        StockExchange exchangeById = exchangeService.getExchangeById(id);
        if(exchangeById ==null){ throw new StockExchangeNotFoundException("No Stock Exchange found with id "+ id);}
        return ResponseEntity.status(HttpStatus.OK).body(exchangeById);
    }

    
    @PostMapping("/registerCompany")
    public ResponseEntity<Map<String, String>> addCompanyToExchange(@RequestBody CompanyStockCodes csc){
        String status = exchangeService.addCompanyToExchange(csc);
        Map<String, String> map = new HashMap<>();
        map.put("status", status);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @GetMapping("/{id}/companies")
    public ResponseEntity<List<CompanyStockCodes>> getAllCompanies(@PathVariable String id) throws CompanyNotFoundException{
        List<CompanyStockCodes> allCompanies = exchangeService.getAllCompanies(id);
        if(allCompanies.size() ==0){ throw new CompanyNotFoundException("No Companies listed in the StockExchange with id "+ id);}
        return ResponseEntity.status(HttpStatus.OK).body(allCompanies);
    }
    @GetMapping("/{id}/stocks")
    public ResponseEntity<List<StockPrices>> getAllStockPrices(@PathVariable String id) throws StockNotFoundException{
        List<StockPrices> allStocks = exchangeService.getAllStocks(id);
        if(allStocks.size() ==0){ throw new StockNotFoundException("No Stocks present in the StockExchange with id "+id);}
        return ResponseEntity.status(HttpStatus.OK).body(allStocks);
    }
    @GetMapping("/{id}/ipos")
    public ResponseEntity<List<Ipo>> getAllIpos(@PathVariable String id) throws IpoNotFoundException{
        List<Ipo> allIpos = exchangeService.getAllIpos(id);
        if(allIpos.size() ==0){ throw new IpoNotFoundException("No Ipo's present in the StockExchange with id "+id);}
        return ResponseEntity.status(HttpStatus.OK).body(allIpos);
    }

    @GetMapping("/remove/{code}")
    public ResponseEntity<String> removeExchange(@PathVariable String code) throws StockNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(exchangeService.removeEx(code));
    }

    @PostMapping("/remove/company")
    public ResponseEntity<Map<String, String>> removeCompany(@RequestBody CompanyStockCodes company){
        Map<String, String> map = new HashMap<>();
        map.put("status", exchangeService.removeCompany(company));
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    
}