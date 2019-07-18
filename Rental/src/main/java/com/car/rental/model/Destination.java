package com.car.rental.model;

public enum Destination {

	PUNE(0), MUMBAI(200), BANGLORE(1000), DELHI(2050), CHENNAI(1234.5);

	private double distance;

	Destination(double distance) {
		this.distance = distance;
	}

	public double getDistance() {
		return this.distance;
	}

}
