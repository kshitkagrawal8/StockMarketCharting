package com.kshitiz.companyservice.Services;

import java.util.List;

import com.kshitiz.companyservice.Entities.Ipo;
import com.kshitiz.companyservice.Exceptions.CompanyNotFoundException;
import com.kshitiz.companyservice.Exceptions.IpoNotFoundException;
import com.kshitiz.companyservice.Exceptions.RegistrationError;
import com.kshitiz.companyservice.models.Dates;

public interface IpoService {

    String addIpo(Ipo ipo, String name);

    List<Ipo> getAllIpoByCompany(String name) throws CompanyNotFoundException, RegistrationError;

	List<Ipo> getIpoByCompanyStockEx(String name, String stockCode) throws CompanyNotFoundException, RegistrationError;

	List<Ipo> getIpoByRange(Dates dates, String name) throws CompanyNotFoundException, RegistrationError;

	List<Ipo> getAllIpo();

	Ipo getIpo(Integer id);

	String removeIpo(Integer id) throws IpoNotFoundException;


}
