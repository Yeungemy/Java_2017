package com.healthycoderapp;

import static org.junit.Assert.assertThrows;
import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BMICalculatorTest {
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("Start the tests!");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("Finished All the tests!");
	}
	
	@Nested
	class isDietRecommendedTests{
		@ParameterizedTest(name = "weight={0}, height={1}")
		@CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)
		void should_ReturnTrue_When_DietRecommended_1(Double coderWeight, Double coderHeight) {
			//given
			double weight = coderWeight;
			double height = coderHeight;
			
			//when
			boolean recommended = BMICalculator.isDietRecommended(weight, height);
			
			//then	
			assertTrue(recommended);
		}

		@ParameterizedTest(name = "weight={0}, height={1}")
		@CsvSource(value = {"89.0, 1.72", "95.0, 1.75", "110, 1.78"})
		void should_ReturnTrue_When_DietRecommended(Double coderWeight, Double coderHeight) {
			//given
			double weight = coderWeight;
			double height = coderHeight;
			
			//when
			boolean recommended = BMICalculator.isDietRecommended(weight, height);
			
			//then	
			assertTrue(recommended);
		}
		
		@ParameterizedTest
		@ValueSource(doubles = {70.0, 50.0, 60.0})
		void should_ReturnFalse_When_DietNotRecommended(Double coderWeight){
			//given
			double weight = coderWeight;
			double height = 1.90;
			
			//when
			boolean notRecommended = BMICalculator.isDietRecommended(weight, height);
			
			//then
			assertFalse(notRecommended);
		}
		
		@Test
		void should_ThrowArithemeticException_When_HeightZero() {
			//given 
			double weight = 50;
			double height = 0.0;
			
			//when
			Executable executable = () -> BMICalculator.isDietRecommended(weight, height);
			
			//then
			assertThrows(ArithmeticException.class, executable);
		}
	}
	
	@Nested
	@DisplayName("sample inner class display name")
	class findCoderWithWorstBMITests{
		@Test
		void should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty() {
			//given
			List<Coder> coders = new ArrayList<>();
			coders.add(new Coder(1.80, 60));
			coders.add(new Coder(1.82, 98));
			coders.add(new Coder(1.82, 64.7));
			
			//when
			Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
			
			//then
			assertAll(
				() -> assertEquals(1.82, coderWorstBMI.getHeight()),
				() -> assertEquals(98, coderWorstBMI.getWeight())
			);
		}
		
		@Test
		@DisplayName(">>>> sample method display name")
		@DisabledOnOs(OS.LINUX)
		void should_ReturnCoderWithWorstBMIInMs_When_CoderListHas10000Elements() {
			//given
			List<Coder> coders = new ArrayList<>();
			for(int i = 0; i < 10000; i++) {
				coders.add(new Coder(1 + i / 10001, 10 + i / 1000));
			}
			
			//when
			Executable executable = () -> BMICalculator.findCoderWithWorstBMI(coders);
			
			//then
			assertTimeout(Duration.ofMillis(40), executable);
		}
		
		@Test
		void should_ReturnNullWorstBMICoder_When_CoderListIsEmpty() {
			//given
					List<Coder> coders = new ArrayList<>();
					
					//when
					Coder coderWorstBMI = BMICalculator.findCoderWithWorstBMI(coders);
					
					//then
					assertNull(coderWorstBMI);
		}
	}
	
	@Nested
	class getBMIScoresTests{
		@Test
		void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty() {
			//given
			List<Coder> coders = new ArrayList<>();
			coders.add(new Coder(1.80, 60));
			coders.add(new Coder(1.82, 98));
			coders.add(new Coder(1.82, 64.7));
			double[] expectedBMIScores = {18.52, 29.59, 19.53};
			
			//when
			double[] actualBMIScores = BMICalculator.getBMIScores(coders);
			
			//then
			assertArrayEquals(expectedBMIScores, actualBMIScores);
		}
	}
}
