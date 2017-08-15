package com.ajay.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajay.card.MetroStations;
import com.ajay.card.SmartCard;
import com.ajay.dao.SmartCardDAO;
import com.ajay.exception.NoMinBalance;
import com.ajay.fare.FareStrategy;
import com.ajay.fare.WeekdayFare;
import com.ajay.fare.WeekendFare;

@Service
public class MetroService {

	private static final int minBalance = 50;
	//stns ar A1,A2,A3.....A10
	
	@Autowired
	private SmartCardDAO smartCardDAO;
	
	/**
	 * Used initially to activate smart card to user.
	 * 
	 * @param allocatedTo
	 * @param balance
	 * @return
	 * @throws NoMinBalance 
	 */
	public SmartCard activateSmartCard(String allocatedTo, double balance) throws NoMinBalance {
		if (balance < minBalance) {
			throw new NoMinBalance("Require min "+ minBalance +  " balance to activate card");
		}
		SmartCard newSmartCard = new SmartCard(allocatedTo, balance);
		return smartCardDAO.save(newSmartCard);
	}
	
	/**
	 * Used to add the balance to the registered smart card.
	 * 
	 * @param smartCardId
	 * @param addBalance
	 * @return
	 */
	public Optional<SmartCard> addBalance(Long smartCardId, int addBalance) {
		Optional<SmartCard> smartCard = smartCardDAO.findById(smartCardId);
		smartCard.get().setBalance(smartCard.get().getBalance() + addBalance);
		return smartCard;
	}
	
	/**
	 * 
	 * @param card
	 * @param sourceStn
	 * @throws NoMinBalance
	 */
	public SmartCard executeSwipeIn(Long smartCardId, String sourceStn) throws NoMinBalance {
		Optional<SmartCard> smartCard = smartCardDAO.findById(smartCardId);
		if (smartCard.get().getBalance() < minBalance) {
			throw new NoMinBalance("Require min "+ minBalance +  " balance to ride, recharge your smart card");
		}
		smartCard.get().setSourceStation(sourceStn);
		return smartCardDAO.save(smartCard.get());
	}

	/**
	 * 
	 * @param card
	 * @param destinationStn
	 * @throws NoMinBalance 
	 */
	public  SmartCard executeSwipeOut(Long smartCardId, String destinationStn, boolean weekend) throws NoMinBalance {
		Optional<SmartCard> smartCard = smartCardDAO.findById(smartCardId);
		calculateFare(destinationStn, weekend, smartCard);
		return smartCardDAO.save(smartCard.get());
	}

	/**
	 * Business Logic for calculating metro fare.
	 * 
	 * @param destinationStn
	 * @param weekend
	 * @param smartCard
	 * @throws NoMinBalance
	 */
	private void calculateFare(String destinationStn, boolean weekend,  Optional<SmartCard> smartCard) throws NoMinBalance {
		double remainingBalance;
		double lastFare;
		try {
			int noofStationsTravelled = MetroStations.distance(
					MetroStations.valueOf(smartCard.get().getSourceStation()), MetroStations.valueOf(destinationStn));
			if (weekend) {
				lastFare = new WeekendFare().getFarePerStation() * noofStationsTravelled;
				remainingBalance = smartCard.get().getBalance() - lastFare;
			} else {
				lastFare = new WeekdayFare().getFarePerStation() * noofStationsTravelled;
				remainingBalance = smartCard.get().getBalance() - lastFare;
			}
			if (remainingBalance < 0) {
				throw new NoMinBalance("Unable to exit(low balance), contact nearby card operator");
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("Illegal arguments found in the request");
		}
		smartCard.get().setDestinationStation(destinationStn);
		smartCard.get().setBalance(remainingBalance);
		smartCard.get().setLastFare(lastFare);
	}
	
	public List<SmartCard> findAll() {
		return (List<SmartCard>) smartCardDAO.findAll();
	}
}
