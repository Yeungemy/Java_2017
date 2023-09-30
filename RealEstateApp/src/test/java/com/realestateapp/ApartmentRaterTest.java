package com.realestateapp;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class ApartmentRaterTest {
	@Nested
	class rateApartmentTest{
		@Test
		void should_ReturnCorrectRating_When_CorrectApartment() {
			//given
			double area = 5.0;
			BigDecimal price = new BigDecimal(50000);
			Apartment apartment = new Apartment(area, price);
			int expected = 2;
			
			//when
			int actual = ApartmentRater.rateApartment(apartment);
			
			//then
			assertEquals(expected, actual);
		}

		@Test
		void should_ReturnErrorValue_When_IncorrectApartment() {
			//given
			double area = 0.0;
			BigDecimal price = new BigDecimal(50000);
			Apartment apartment = new Apartment(area, price);
			int expected = -1;
			
			//when
			int actual = ApartmentRater.rateApartment(apartment);
			
			//then
			assertEquals(expected, actual);
		}
	}

	@Nested
	class calculateAverageRatingTest{
		@Test
		void should_CalculateAverageRating_When_CorrectApartmentList() {
			//given
			List<Apartment> apartments = new ArrayList<>();
			apartments.add(new Apartment(2500.0, new BigDecimal(1500000)));
			apartments.add(new Apartment(5000.0, new BigDecimal(2500000)));
			apartments.add(new Apartment(8000.0, new BigDecimal(3500000)));
			apartments.add(new Apartment(12000.0, new BigDecimal(4500000)));
			double expected = 0.0;
			
			//when
			double actual = ApartmentRater.calculateAverageRating(apartments);
			
			//then
			assertEquals(expected, actual);
		}
		
		@Test
		void should_ThrowExceptionInCalculateAverageRating_When_EmptyApartmentList() {
			//given
			List<Apartment> apartments = new ArrayList<>();
			
			//when
			Executable executable = () -> ApartmentRater.calculateAverageRating(apartments);
			
			//then
			assertThrows(RuntimeException.class, executable);
		}
	}
}
