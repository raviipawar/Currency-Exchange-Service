package com.learning.hotelservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.hotelservice.entities.ExchangeValue;
import com.learning.hotelservice.services.CurrencyExchangeService;

@RestController
public class CurrencyExchangeController {

	private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@Autowired
	private CurrencyExchangeService currencyExchangeService;
	
	@PostMapping("/add")
	public ResponseEntity<ExchangeValue> createHotel(@RequestBody ExchangeValue exchangeValue) {
		ExchangeValue exchangeValue1 = currencyExchangeService.saveExchangeValue(exchangeValue);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(exchangeValue1);
	}
	
	@GetMapping("/currency-exchanges/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		ExchangeValue exchangeValue = currencyExchangeService.findByFromAndTo(from, to);
		logger.info("retrieveExchangeValue method callled with {}  to {}", from , to);
		return exchangeValue;
	}
}
