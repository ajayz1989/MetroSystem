package com.ajay.fare;

public class WeekendFare implements FareStrategy{

	@Override
	public String getName() {
		return WeekendFare.class.toGenericString();
	}

	@Override
	public double getFarePerStation() {
		return 5.5;
	}

}
