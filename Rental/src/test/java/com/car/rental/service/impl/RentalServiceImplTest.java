package com.car.rental.service.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.car.rental.exception.InvalidArgumentException;
import com.car.rental.model.AirCondition;
import com.car.rental.model.Destination;
import com.car.rental.model.Fuel;
import com.car.rental.model.Trip;
import com.car.rental.model.Vehicle;
import com.car.rental.repository.RentalRepository;
import com.car.rental.service.RentalService;

@RunWith(SpringJUnit4ClassRunner.class)
public class RentalServiceImplTest {

	public static final String AC = "AC";
	public static final String NONAC = "NONAC";
	public static final String DIESEL = "DIESEL";
	public static final String PETROL = "PETROL";
	public static final String CAR = "CAR";
	public static final String BUS = "BUS";
	public static final String SUV = "SUV";
	public static final String PUNE = "PUNE";
	public static final String DELHI = "DELHI";
	public static final String MUMBAI = "MUMBAI";
	public static final String BANGLORE = "BANGLORE";
	public static final String CHENNAI = "CHENNAI";

	@TestConfiguration
	static class AccountServiceTestContextConfiguration {
		@Bean
		public RentalService rentalService() {
			return new RentalServiceImpl();
		}
	}
	

	@Autowired
	private RentalService rentalService;

	@MockBean
	private RentalRepository rentalRepository;

	@Test(expected = InvalidArgumentException.class)
	public void testCheckTripValidity_whenCityPune_thenThowException() throws InvalidArgumentException {
		Trip trip = new Trip(BUS, AC, DIESEL, PUNE, 20);
		rentalService.checkTripValidity(trip);
		fail("Test Failed");
	}

	@Test
	public void testCheckTripValidity_whenCityNotPune_thenPassTest() throws InvalidArgumentException {
		Mockito.when(rentalRepository.getVehicle(BUS)).thenReturn(Vehicle.valueOf(BUS));

		Trip trip = new Trip(BUS, AC, DIESEL, BANGLORE, 20);
		rentalService.checkTripValidity(trip);

	}

	@Test(expected = InvalidArgumentException.class)
	public void testCheckTripValidity_whenTripTypeIsNONAC_AndVehicleTypeIsOnlyAC_thenThrowException()
			throws InvalidArgumentException {
		Mockito.when(rentalRepository.getVehicle(SUV)).thenReturn(Vehicle.valueOf(SUV));

		Trip trip = new Trip(SUV, NONAC, DIESEL, BANGLORE, 20);
		rentalService.checkTripValidity(trip);

	}

	@Test(expected = InvalidArgumentException.class)
	public void testCheckTripValidity_whenTripTypeIsPetrol_AndVehicleTypeIsOnlyDiesel_thenThrowException()
			throws InvalidArgumentException {
		Mockito.when(rentalRepository.getVehicle(SUV)).thenReturn(Vehicle.valueOf(SUV));

		Trip trip = new Trip(SUV, NONAC, PETROL, BANGLORE, 20);
		rentalService.checkTripValidity(trip);

	}

	@Test
	public void testCalculateCostForRoundTrip_whenVehicleIsSUVWithNoExtraPassenger() {
		mockRepository(SUV, DELHI, DIESEL, AC);
		Trip trip = new Trip(SUV, AC, DIESEL, DELHI, 7);
		double tripCost = rentalService.calculateCostForRoundTrip(trip);
		assertEquals(65600.0, tripCost, 0.0);

	}

	@Test
	public void testCalculateCostForRoundTrip_whenVehicleIsSUVWithExtraPassenger() {

		mockRepository(SUV, DELHI, DIESEL, AC);
		Trip trip = new Trip(SUV, AC, DIESEL, DELHI, 8);
		double tripCost = rentalService.calculateCostForRoundTrip(trip);
		assertEquals(98400.0, tripCost, 0.0);

	}
	
	@Test
	public void testCalculateCostForRoundTrip_whenVehicleIsCARAndNONAC() {

		mockRepository(CAR, DELHI, DIESEL, NONAC);
		Trip trip = new Trip(CAR, NONAC, DIESEL, DELHI, 5);
		double tripCost = rentalService.calculateCostForRoundTrip(trip);
		assertEquals(57400.0, tripCost, 0.0);

	}
	
	@Test
	public void testCalculateCostForRoundTrip_whenVehicleIsCARAndPETROL() {

		mockRepository(CAR, DELHI, PETROL, NONAC);
		Trip trip = new Trip(CAR, NONAC, PETROL, DELHI, 5);
		double tripCost = rentalService.calculateCostForRoundTrip(trip);
		assertEquals(61500.0, tripCost, 0.0);

	}
	
	@Test
	public void testCalculateCostForRoundTrip_whenVehicleIsBUSAndWithDiscount() {

		mockRepository(BUS, DELHI, DIESEL, NONAC);
		Trip trip = new Trip(BUS, NONAC, DIESEL, DELHI, 15);
		double tripCost = rentalService.calculateCostForRoundTrip(trip);
		assertEquals(56252.0000256598, tripCost, 0.0);

	}
	
	

	private void mockRepository(String vehicle, String city, String fuel, String airCondition) {
		Mockito.when(rentalRepository.getVehicle(vehicle)).thenReturn(Vehicle.valueOf(vehicle));

		Mockito.when(rentalRepository.getDestination(city)).thenReturn(Destination.valueOf(city));

		Mockito.when(rentalRepository.getFuel(fuel)).thenReturn(Fuel.valueOf(fuel));

		Mockito.when(rentalRepository.getAirCondtion(airCondition)).thenReturn(AirCondition.valueOf(airCondition));
	}

}
