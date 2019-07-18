package com.car.rental.model;

public enum AirCondition {

	AC(2), NONAC(0);
	
	private int charge;
	
	AirCondition(int charge){
		this.charge = charge;
	}

	public int getCharge() {
		return charge;
	}
	
	
}
