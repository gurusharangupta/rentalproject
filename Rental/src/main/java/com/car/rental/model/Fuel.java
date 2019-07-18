package com.car.rental.model;

public enum Fuel {

	PETROL(15), DIESEL(14);

	private int cost;

	Fuel(int cost) {
		this.cost = cost;
	}

	public int getCost() {
		return cost;
	}

}
