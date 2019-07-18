package com.car.rental.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.rental.exception.InvalidArgumentException;
import com.car.rental.model.AirCondition;
import com.car.rental.model.Destination;
import com.car.rental.model.Fuel;
import com.car.rental.model.Trip;
import com.car.rental.model.Vehicle;
import com.car.rental.repository.RentalRepository;
import com.car.rental.service.RentalService;

@Service
public class RentalServiceImpl implements RentalService {

	public static final String AC = "AC";
	public static final String NONAC = "NONAC";
	public static final String DIESEL = "DIESEL";
	public static final String PETROL = "PETROL";
	public static final String BUS = "BUS";
	public static final String DESTINATION_EXCEPTION = "Origin and Destinations both cannot be same city";
	public static final String AIRCONDITION_EXCEPTION = "Vehicle selected doesnt provide the Aircondition type selected";
	public static final String FUEL_EXCEPTION = "Vehicle selected doesnt provide the Fuel type selected";

	@Autowired
	private RentalRepository rentalRepository;

	public void checkTripValidity(Trip trip) throws InvalidArgumentException {

		Vehicle vehicle = rentalRepository.getVehicle(trip.getVehicleType());

		if (trip.getCity().equals(Destination.PUNE.name())) {
			throw new InvalidArgumentException(DESTINATION_EXCEPTION);
		}

		if ((trip.getAirConditionType().equals(NONAC) && vehicle.getAirConditionType().equals(AC))
				|| (trip.getAirConditionType().equals(AC) && vehicle.getAirConditionType().equals(NONAC))) {
			throw new InvalidArgumentException(AIRCONDITION_EXCEPTION);
		}

		if ((trip.getFuelType().equals(PETROL) && vehicle.getFuelType().equals(DIESEL))
				|| (trip.getFuelType().equals(DIESEL) && vehicle.getFuelType().equals(PETROL))) {
			throw new InvalidArgumentException(FUEL_EXCEPTION);
		}

	}

	@Override
	public double calculateCostForRoundTrip(Trip trip) {
		double distance = calculateRoundTripDistance(trip.getCity());
		double tripCost = calculateFuelCostOfVehicle(trip.getFuelType(), distance);
		tripCost -= calculateDiscount(trip.getVehicleType(), tripCost);

		if (trip.getAirConditionType().equals(AC)) {
			tripCost += calculateCostForAcTrip(distance);
		}
		tripCost += calculateCostForExtraPassenger(trip.getVehicleType(), trip.getPassenger(), distance);
		return tripCost;

	}

	private double calculateRoundTripDistance(String city) {
		Destination destination = rentalRepository.getDestination(city);
		double distance = destination.getDistance() * 2;
		return distance;

	}

	private double calculateFuelCostOfVehicle(String fuelType, double distance) {
		Fuel fuel = rentalRepository.getFuel(fuelType);
		double tripCost = distance * fuel.getCost();
		return tripCost;
	}

	private double calculateCostForAcTrip(double distance) {
		AirCondition aircondition = rentalRepository.getAirCondtion(AC);
		double tripCost = distance * aircondition.getCharge();
		return tripCost;
	}

	private double calculateCostForExtraPassenger(String vehicleType, int passenger, double distance) {

		Vehicle vehicle = rentalRepository.getVehicle(vehicleType);
		if (passenger > vehicle.getSeats()) {
			double tripCost = distance * passenger;
			return tripCost;
		}
		return 0;
	}

	private double calculateDiscount(String vehicleType, double tripCost) {
		Vehicle vehicle = rentalRepository.getVehicle(vehicleType);
		if (vehicle.getDiscount() != 0) {
			double cost = (vehicle.getDiscount() / 100) * tripCost;
			return cost;
		}
		return 0;

	}

}
