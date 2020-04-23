package com.cognizant.ormlearn;
import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.exception.CountryNotFoundException;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;

@SpringBootApplication(scanBasePackages = {"com.cognizant.ormlearn.service","com.cognizant.ormlearn.repository","com.cognizant.ormlearn.model"})
public class OrmLearnApplication {

	private static CountryService countryService;

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
		countryService = context.getBean(CountryService.class);
		testDeleteCountry();
	}
	private static void testGetAllCountries() {
		LOGGER.info("Start");
		List<Country> countries = countryService.getAllCountries();
		LOGGER.debug("countries={}", countries);
		LOGGER.info("End");
	}
	private static void getAllCountryCodeTest() throws CountryNotFoundException {
		LOGGER.info("Start");
		Country country = countryService.findCountryByCode("AS");
		LOGGER.debug("Country={}", country);
		LOGGER.info("End");
	}
	private static void testAddCountry() {
		LOGGER.info("Start");
		Country country=new Country("XY","Xyzab");
		countryService.addCountry(country);
		try {
			LOGGER.debug("Country={}",countryService.findCountryByCode(country.getCountryCode()));
		} catch (CountryNotFoundException e) {
			e.printStackTrace();
		}
		LOGGER.info("End");
	}
	
	private static void testUpdateCountry() {
		LOGGER.info("Start");
		Country country=new Country("XY","Abcde");
		countryService.updateCountry(country);
		try {
			LOGGER.debug("Country={}",countryService.findCountryByCode(country.getCountryCode()));
		} catch (CountryNotFoundException e) {
			e.printStackTrace();
		}
		LOGGER.info("End");
	}
	
	private static void testDeleteCountry() {
		LOGGER.info("Start");
		countryService.deleteCountry("XY");
		LOGGER.info("End");
	}
}
