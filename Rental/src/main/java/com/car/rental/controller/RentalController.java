package com.car.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.rental.exception.InvalidArgumentException;
import com.car.rental.model.Expense;
import com.car.rental.model.Trip;
import com.car.rental.service.RentalService;

@RestController
public class RentalController {

	@Autowired
	private RentalService rentalService;

	@GetMapping("/expense")
	public ResponseEntity<Expense> calculateExpense(@RequestParam(required=true,name="vehicleType") String vehicleType,
			@RequestParam(required=true,name="airConditionType") String airConditionType,
			@RequestParam(required=true,name="fuelType") String fuelType,
			@RequestParam(required=true,name="city") String city,
			@RequestParam(required=true,name="passenger") int passenger)throws InvalidArgumentException {
			
		Expense expense = new Expense();
		Trip trip = new Trip(vehicleType,airConditionType,fuelType,city.toUpperCase(),passenger);
		rentalService.checkTripValidity(trip);
		double tripCost = rentalService.calculateCostForRoundTrip(trip);
		expense.setCost(tripCost);
		expense.setMessage("Cost for " + trip.getAirConditionType() + " " + trip.getVehicleType() + " travel to "
				+ trip.getCity());
		return new ResponseEntity<>(expense, HttpStatus.OK);
	}
	

}
