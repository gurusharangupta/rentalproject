package com.car.rental.model;

public class Trip {

	private String vehicleType;
	private String airConditionType;
	private String fuelType;
	private String city;
	private int passenger;

	public Trip(String vehicleType, String airConditionType, String fuelType, String city, int passenger) {
		super();
		this.vehicleType = vehicleType;
		this.airConditionType = airConditionType;
		this.fuelType = fuelType;
		this.city = city;
		this.passenger = passenger;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getAirConditionType() {
		return airConditionType;
	}

	public void setAirConditionType(String airConditionType) {
		this.airConditionType = airConditionType;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public int getPassenger() {
		return passenger;
	}

	public void setPassenger(int passenger) {
		this.passenger = passenger;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
