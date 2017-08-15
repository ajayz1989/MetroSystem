package com.ajay.exception;

public class NoMinBalance extends Exception{

	public NoMinBalance(String message, Throwable cause) {
		super(message, cause);
	}

	public NoMinBalance(String message) {
		super(message);
	}
	
}
