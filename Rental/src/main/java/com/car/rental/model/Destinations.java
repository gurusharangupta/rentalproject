package com.car.rental.model;

public enum Destinations {

	Pune(0), Mumbai(200), Bangalore(1000), Delhi(2050), Chennai(1234.5);

	private double distance;

	Destinations(double distance) {
		this.distance = distance;
	}

	public double getDistance() {
		return this.distance;
	}

}
