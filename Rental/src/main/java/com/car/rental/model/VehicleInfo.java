package com.car.rental.model;

public enum VehicleInfo {

	CAR("CAR", 5, 6), SUV("SUV", 7, 9), VAN("VAN", 15, 18), BUS("BUS", 30, 35);

	private int seats;
	private int maxLimit;
	private String vehicleType;

	VehicleInfo(String vehicleType, int seats, int maxLimit) {
		this.seats = seats;
		this.maxLimit = maxLimit;
		this.vehicleType = vehicleType;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public int getSeats() {
		return this.seats;
	}

	public int getMaxLimit() {
		return this.maxLimit;
	}

}
