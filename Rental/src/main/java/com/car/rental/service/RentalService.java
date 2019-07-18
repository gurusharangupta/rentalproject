package com.car.rental.service;

import org.springframework.stereotype.Service;

import com.car.rental.exception.InvalidArgumentException;
import com.car.rental.model.Trip;


public interface RentalService {

	public void checkTripValidity(Trip trip) throws InvalidArgumentException;
	public double calculateCostForRoundTrip(Trip trip);
	
}
