package com.car.rental.repository;

import org.springframework.stereotype.Repository;

import com.car.rental.model.AirCondition;
import com.car.rental.model.Destination;
import com.car.rental.model.Fuel;
import com.car.rental.model.Vehicle;


public interface RentalRepository {

	public Vehicle getVehicle(String vehicleType);

	public Fuel getFuel(String fuel);

	public Destination getDestination(String destination);
	
	public AirCondition getAirCondtion(String airCondition);

}
