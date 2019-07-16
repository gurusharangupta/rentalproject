package com.car.rental.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car.rental.model.Expense;
import com.car.rental.model.Trip;

@RestController
public class RentalController {

	@PostMapping("/expense")
	public ResponseEntity<Expense> calculateExpense(Trip trip) {

		
		return null;
	}

}
