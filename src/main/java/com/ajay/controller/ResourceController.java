package com.ajay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajay.card.SmartCard;
import com.ajay.exception.NoMinBalance;
import com.ajay.service.MetroService;

@RestController
public class ResourceController {

	@Autowired
	private MetroService metroService;
	
	@RequestMapping("/hello")
	public String index() {
		return "login";
	}
	
	@GetMapping(path = "/activate/{username}/{balance}")
	public ResponseEntity<SmartCard> activateCard(@PathVariable("username") String userName , @PathVariable("balance") double initialBal ) throws NoMinBalance {
		return new ResponseEntity<SmartCard>(metroService.activateSmartCard(userName, initialBal),HttpStatus.OK);
	}
	
	@GetMapping(path = "/cards")
	public @ResponseBody List<SmartCard> viewAllSmartCards() {
		return metroService.findAll();
	}
	
	@GetMapping(path = "/swipein/{id}/{source}")
	public SmartCard swipeIn(@PathVariable("id") Long smartCardId , @PathVariable("source") String source ) throws NoMinBalance {
		return metroService.executeSwipeIn(smartCardId, source);
	}
	
	@GetMapping(path = "/swipeout/{id}/{destination}/{weekend}")
	public SmartCard swipeOut(@PathVariable("id") Long smartCardId , @PathVariable("destination") String source, @PathVariable("weekend") boolean weekend ) throws NoMinBalance {
		return metroService.executeSwipeOut(smartCardId, source, weekend);
	}
}
