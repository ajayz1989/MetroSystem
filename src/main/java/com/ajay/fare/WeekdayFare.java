package com.ajay.fare;

public class WeekdayFare implements FareStrategy{

	@Override
	public String getName() {
		return WeekdayFare.class.toGenericString();
	}

	@Override
	public double getFarePerStation() {
		return 7;
	}

}
