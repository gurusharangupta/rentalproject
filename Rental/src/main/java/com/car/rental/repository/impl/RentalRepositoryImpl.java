package com.car.rental.repository.impl;

import org.springframework.stereotype.Repository;

import com.car.rental.model.AirCondition;
import com.car.rental.model.Destination;
import com.car.rental.model.Fuel;
import com.car.rental.model.Vehicle;
import com.car.rental.repository.RentalRepository;

@Repository
public class RentalRepositoryImpl implements RentalRepository {

	public Vehicle getVehicle(String vehicleType) {
		return Vehicle.valueOf(vehicleType);
	}

	public Fuel getFuel(String fuel) {
		return Fuel.valueOf(fuel);
	}

	public Destination getDestination(String destination) {
		return Destination.valueOf(destination);
	}

	@Override
	public AirCondition getAirCondtion(String airCondition) {
		return AirCondition.valueOf(airCondition);
	}

}