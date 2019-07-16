package com.car.rental.model;

public enum Fuel {

	PETROL(15), DIESEL(14);

	private int fuel;

	Fuel(int fuel) {
		this.fuel = fuel;
	}

	public int getFuel() {
		return fuel;
	}

}
