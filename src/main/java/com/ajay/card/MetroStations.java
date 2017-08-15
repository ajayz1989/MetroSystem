package com.ajay.card;

public enum MetroStations {

	A1,
	A2,
	A3,
	A4,
	A5,
	A6,
	A7,
	A8,
	A9,
	A10;
	
	public static int distance(MetroStations source , MetroStations destination) {
		return Math.abs(destination.ordinal() - source.ordinal());
	}
}
