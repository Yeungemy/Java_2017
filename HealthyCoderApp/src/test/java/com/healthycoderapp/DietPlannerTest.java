package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DietPlannerTest {
	private DietPlanner dietPlanner;
	
	@BeforeEach
	void setup() {
		this.dietPlanner = new DietPlanner(20, 30, 50);
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("A unit test was finished.");
	}

	@Test
	void should_ReturnCorrectDietPlan_When_CorrectCoder() {
		//given
		Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
		DietPlan expectedDietPlan = new DietPlan(2202, 110, 73, 275);
		
		//when
		DietPlan actualDietPlan = dietPlanner.calculateDiet(coder);
		
		//then
		assertAll(
			() -> assertEquals(expectedDietPlan.getCalories(), actualDietPlan.getCalories()),
			() -> assertEquals(expectedDietPlan.getProtein(), actualDietPlan.getProtein()),	
			() -> assertEquals(expectedDietPlan.getCarbohydrate(), actualDietPlan.getCarbohydrate())
		);		
	}
}
