# MetroSystem
Application to book metro ticket through smart card

# Problem Statement :
Our Company has been asked to implement Metro Smart Card System (MSCS) for Delhi City. For this application assume there is a single metro line covering 10 stations linearly.

The stations name are A1,A2,A3,A4,A5,A6,A7,...A10. The travel can be in any direction from any station except A1 and A10.

Travelers have smart card that behave just like any other regular debit card that has an initial balance when purchased. Travelers swipe in when they enter a metro station and swipe out when they exit. Card balance is automatically updated at swipe out.

# Objective :

Objective of this exercise is to create an automated system that has following functionality.

Card should have a minimum balance of Rs. 50 at swipe-in. At swipe-out, system should calculate the fare based on below strategies. The fare must be deducted from the card. Card should have the sufficient balance otherwise user should not be able to exit.

Weekday - Rs 7 * (Number of stations travelled)
Weekend - Rs 5.5 * (Number of station traveled if it is Saturday or Sunday )
(* there could be more such fare strategies in future).

Additionally system needs to have functionality to generate some statistics/report defined below. So system needs to provide following API.


API to get total foot-fall(swipe-in + swipe-out) for a given station.
API to generate a "per-card report" on demand. It should print the following information on console.
      Card<number> used to travel from <source_station> to station <destination_station>. Fare is Rs<x> and balance on card is Rs<x>.
	  
	  
# Application is an REST API based application ,after running can use following APIs :-

http://localhost:8080/hello --> to verify & test the api is working

http://localhost:8080/activate/{username}/{balance} --> to activate a metro smart card to the user, it will give the registered smart card details.

http://localhost:8080/cards --> to fetch all the the cards from the database(I have used H2 in memory database currently http://localhost:8080/h2 )

http://localhost:8080/swipein/{id}/{source} --> to swipein a smard card from the source station 

http://localhost:8080/swipeout/{id}/{destination}/{weekend} --> to swipe out a smart card from a destination station and bill user based on boolean weekend/weekday
