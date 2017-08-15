package com.ajay.card;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SmartCard {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long smartCardId;
	private double balance ;
	private String allocatedTo;
	private String sourceStation;
	private String destinationStation;
	private double lastFare;
	
	public SmartCard() {
	}
	
	public SmartCard(String allocatedTo, double balance) {
		super();
		this.balance = balance;
		this.allocatedTo = allocatedTo;
	}
	

	public Long getSmartCardId() {
		return smartCardId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAllocatedTo() {
		return allocatedTo;
	}

	public void setAllocatedTo(String allocatedTo) {
		this.allocatedTo = allocatedTo;
	}

	public String getSourceStation() {
		return sourceStation;
	}

	public void setSourceStation(String sourceStation) {
		this.sourceStation = sourceStation;
	}

	public String getDestinationStation() {
		return destinationStation;
	}

	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}

	public double getLastFare() {
		return lastFare;
	}

	public void setLastFare(double lastFare) {
		this.lastFare = lastFare;
	}

}
