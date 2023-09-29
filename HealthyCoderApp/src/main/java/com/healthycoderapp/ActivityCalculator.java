package com.healthycoderapp;

public class ActivityCalculator {
	private static final int WORKOUT_DURATION_MIN = 45;
	
	public static String rateActivityLevel(int weeklyCardioMin, int weeklyWorkoutSession) {
		if(weeklyCardioMin < 0 || weeklyWorkoutSession < 0) {
			throw(new RuntimeException("Negative value is not allowed"));
		}
		
		int totalMins = weeklyCardioMin + weeklyWorkoutSession * WORKOUT_DURATION_MIN;
		double avg = totalMins / 7.0;
		
		if(avg < 20) {
			return "bad";
		}else if(avg < 40) {
			return "average";
		}else {
			return "good";
		}
	}
}
