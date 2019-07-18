package com.car.rental.model;

public enum Vehicle {

	CAR(5,"BOTH", "BOTH",0), SUV(7,"AC", "DIESEL",0), VAN(15,"BOTH", "DIESEL",0), BUS(30,"BOTH", "DIESEL",2);

	private int seats;
//	private int maxSeatLimit;
	private String airConditionType;
	private String fuelType;
	private float discount;

	Vehicle(int seats, String airConditionType, String fuelType, float discount) {
		this.seats = seats;
		this.airConditionType = airConditionType;
		this.fuelType = fuelType;
		this.discount = discount;

	}

	public int getSeats() {
		return this.seats;
	}

	public String getAirConditionType() {
		return airConditionType;
	}

	public String getFuelType() {
		return fuelType;
	}

	public float getDiscount() {
		return discount;
	}


}
